package cn.edu.cess.config.datasource.druid;

import cn.edu.cess.util.CommonUtil;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Package: cn.edu.cess.config
 * @Description:xxlJob数据源配置
 * @Author: LuDeSong
 * @Date: 2021-7-22 15:11
 */
@Configuration
@MapperScan(basePackages = {"cn.edu.cess.mapper.xxljob"})
public class DruidConfigXxlJob {

    public static final String XXL_DATA_SOURCE = "xxlDataSource";

    @Value("${xxl.mapper-locations}")
    private String mapperLocation;

    /**
     * 连接数据库信息 这个其实更好的是用配置中心完成
     */
    @Value("${datasource.xxl.url}")
    private String url;

    @Value("${datasource.xxl.username}")
    private String username;

    @Value("${datasource.xxl.password}")
    private String password;

    @Value("${datasource.xxl.driver-class-name}")
    private String driverClassName;


    /**
     * 下面的配置信息可以读取配置文件，其实可以直接写死 如果是多数据源的话 还是考虑读取配置文件
     */
    @Value("${datasource.initialSize}")
    private int initialSize;

    @Value("${datasource.minIdle}")
    private int minIdle;

    @Value("${datasource.maxActive}")
    private int maxActive;

    @Value("${datasource.maxWait}")
    private int maxWait;

    @Value("${datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${datasource.validationQuery}")
    private String validationQuery;

    @Value("${datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${datasource.filters}")
    private String filters;

    @Value("${datasource.connectionProperties}")
    private String connectionProperties;


    @Bean(name = XXL_DATA_SOURCE)
    @Primary
    public DataSource xxlDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);

        //具体配置
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        /**
         * 这个是用来配置 druid 监控sql语句的 非常有用 如果你有两个数据源 这个配置哪个数据源就监控哪个数据源的sql 同时配置那就都监控
         */
        try {
            dataSource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.setConnectionProperties(connectionProperties);
        return dataSource;
    }

    //事物管理，默认的DataSourceTransactionManager是属于DataSourceTransactionManager的，如果要开启另一个数据库事务，需要手工指定一下这个manager，不然用的都是第一个db的事务管理器，导致事务不生效
    @Bean(name = "xxlTransactionManager")
    @Primary
    public DataSourceTransactionManager xxlTransactionManager() {
        return new DataSourceTransactionManager(xxlDataSource());
    }

    //Mybatis专属配置
    @Bean(name = "xxlSqlSessionFactory")
    @Primary
    public SqlSessionFactory xxlSqlSessionFactory()
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(xxlDataSource());
        sessionFactory.setMapperLocations(CommonUtil.resolveMapperLocations(mapperLocation));
        return sessionFactory.getObject();
    }

    @Primary
    @Bean("xxlSqlSessionTemplate")
    public SqlSessionTemplate xxlSqlSessionTemplate() throws Exception {
        // 使用上面配置的Factory
        SqlSessionTemplate template = new SqlSessionTemplate(xxlSqlSessionFactory());
        return template;
    }

}
