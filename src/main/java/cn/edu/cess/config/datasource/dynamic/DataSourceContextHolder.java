package cn.edu.cess.config.datasource.dynamic;

import cn.edu.cess.config.datasource.druid.DruidConfigMaster;
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

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源名
    public static void setDB(String dbType) {
        log.info("DataSourceContextHolder 切换到{" + dbType + "}数据源");
        contextHolder.set(dbType);
    }

    // 获取数据源名
    public static String getDB() {
        return (contextHolder.get());
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }
}
