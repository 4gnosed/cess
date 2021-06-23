package cn.edu.cess.config.datasource.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Package: cn.edu.cess.config.datasource
 * @Description:动态数据源配置
 * @Author: LuDeSong
 * @Date: 2021-6-23 20:27
 */

public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        String db = DataSourceContextHolder.getDB();
        log.info("DynamicDataSource 当前数据源：" + db);
        return db;
    }
}
