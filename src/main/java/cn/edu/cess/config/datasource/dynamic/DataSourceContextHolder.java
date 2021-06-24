package cn.edu.cess.config.datasource.dynamic;

import cn.edu.cess.config.datasource.druid.DruidConfigMaster;
import cn.edu.cess.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Package: cn.edu.cess.config
 * @Description:数据源上下文
 * @Author: LuDeSong
 * @Date: 2021-6-23 20:23
 */

public class DataSourceContextHolder {
    private static final Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);

    //默认数据源
    public static final String DEFAULT_DS = DruidConfigMaster.MASTER_DATA_SOURCE;

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    // 设置数据源名
    public static void setDB(String dbType) {
        log.info("DataSourceContextHolder 手动切换数据源：" + dbType);
        THREAD_LOCAL.set(dbType);
    }

    // 获取数据源名
    public static String getDB() {
        String s = THREAD_LOCAL.get();
        if (StringUtil.isEmpty(s)) {
            log.info("DataSourceContextHolder 默认数据源：" + DEFAULT_DS);
            return DEFAULT_DS;
        }
        return s;
    }

    // 清除数据源名
    public static void clearDB() {
        THREAD_LOCAL.remove();
    }
}
