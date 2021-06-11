package cn.edu.cess.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 雪花算法生成器
 */
public class SnowFlakeId {

    private Logger log = LoggerFactory.getLogger(getClass());
    // ==============================Fields===========================================
    /**
     * 开始时间截 (2015-01-01)
     */
    private final long twepoch = 1420041600000L;

    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 5L;

    /**
     * 数据标识id所占的位数
     */
    private final long datacenterIdBits = 5L;

    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 支持的最大数据标识id，结果是31
     */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 数据标识id向左移17位(12+5)
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间截向左移22位(5+5+12)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器ID(0~31)
     */
    private long workerId = 0L;

    /**
     * 数据中心ID(0~31)
     */
    private long datacenterId = 0L;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    //如果时间倒退，则启用以下参数补救
    /**
     * 上次生成ID的时间截--临时的
     */
    private long lastTimestampTemp = -1L;

    public static SnowFlakeId snowflake = new SnowFlakeId();


    // ==============================Constructors=====================================

    /**
     * 构造函数
     */
    public SnowFlakeId() {

        //workerId 工作ID 第几个服务,最大值不可以超过1023，最多一个机房可以扩1024个服务，算上机器复用
        //datacenterId  数据中心ID (0~31)
        Integer workerId = ConfigUtil.getIntProperty("snowflake.workId");
        Integer datacenterId = ConfigUtil.getIntProperty("snowflake.datacenterId");
        log.info("-----snowflakeId---------读取环境变量配置文件,workerId:" + workerId);
        log.info("-----snowflakeId---------从外部读取配置文件,datacenterId:" + datacenterId);
        if (workerId == null || datacenterId == null) {
            workerId = new Integer(1);
            datacenterId = new Integer(1);
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;

        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
    }

    public static long generate() {
        return snowflake.nextId();
    }

    public static long generate(int bitNum) {

        long id = snowflake.nextId();

        String ids = String.valueOf(id);

        return Long.parseLong(ids.substring(ids.length() - bitNum));
    }

    // ==============================Methods==========================================

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
//            throw new RuntimeException(String.format(
//                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
            log.info(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
            //时钟回退这个时候不再抛出异常，用最大数据标识id和最大的机器id去生成ID，临时补救措施。如果机器机器达到1024个节点的量级，还是会冲突。add by wanghuai20190521 begin
            // 如果是同一时间生成的，则进行毫秒内序列
            if (lastTimestampTemp == timestamp) {
                sequence = (sequence + 1) & sequenceMask;
                // 毫秒内序列溢出
                if (sequence == 0) {
                    // 阻塞到下一个毫秒,获得新的时间戳
                    timestamp = tilNextMillis(lastTimestampTemp);
                }
            }
            // 时间戳改变，毫秒内序列重置
            else {
                sequence = 0L;
            }

            lastTimestampTemp = timestamp;
            // 移位并通过或运算拼到一起组成64位的ID（如果时间倒退，则用最大的数据中心和最大的机器ID去生成）
            return ((timestamp - twepoch) << timestampLeftShift) //
                    | (maxDatacenterId << datacenterIdShift) //
                    | (maxWorkerId << workerIdShift) //
                    | sequence;

            //时钟回退这个时候不再抛出异常，用最大数据标识id和最大的机器id去生成ID，临时补救措施。如果机器机器达到1024个节点的量级，还是会冲突。add by wanghuai20190521 end

        }

        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        // 时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        // 上次生成ID的时间截
        lastTimestamp = timestamp;

        // 移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    // ==============================Test===========================================

    /**
     * 测试
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            System.out.println(SnowFlakeId.generate());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗时" + (endTime - startTime) + "ms");

    }
}
