package cn.edu.cess.config.datasource;

import cn.edu.cess.config.datasource.dynamic.DynamicDataSource;
import cn.edu.cess.util.CommonUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Gnosed Lu
 * @Date 2020/3/30
 * @Description
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"cn.edu.cess.mapper"})
public class MybatisPlusConfig {

    public static final String MASTER_DATA_SOURCE = "masterDataSource";
    public static final String CLUSTER_DATA_SOURCE = "clusterDataSource";
    public static final String XXL_DATA_SOURCE = "xxlDataSource";

    @Value("${mybatis-plus.mapper-locations}")
    private String mapperLocation;

    /**
     * 主库参数
     */
    @Value("${datasource.master.url}")
    private String masterUrl;
    @Value("${datasource.master.username}")
    private String masterUsername;
    @Value("${datasource.master.password}")
    private String masterPassword;
    @Value("${datasource.master.driver-class-name}")
    private String masterDriverClassName;
    /**
     * 从库参数
     */
    @Value("${datasource.cluster.url}")
    private String clusterUrl;
    @Value("${datasource.cluster.username}")
    private String clusterUsername;
    @Value("${datasource.cluster.password}")
    private String clusterPassword;
    @Value("${datasource.cluster.driver-class-name}")
    private String clusterDriverClassName;
    /**
     * 从库参数
     */
    @Value("${datasource.xxl.url}")
    private String xxlUrl;
    @Value("${datasource.xxl.username}")
    private String xxlUsername;
    @Value("${datasource.xxl.password}")
    private String xxlPassword;
    @Value("${datasource.xxl.driver-class-name}")
    private String xxlDriverClassName;

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

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 初始化主库，可读写
     *
     * @return
     */
    @Bean(name = MASTER_DATA_SOURCE)
//    @ConfigurationProperties(prefix = "datasource.master")
    public DataSource master() {
//        return DruidDataSourceBuilder.create().build();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(masterUrl);
        dataSource.setUsername(masterUsername);
        dataSource.setPassword(masterPassword);
        dataSource.setDriverClassName(masterDriverClassName);

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

    /**
     * 初始化从库，只读
     *
     * @return
     */
    @Bean(name = CLUSTER_DATA_SOURCE)
//    @ConfigurationProperties(prefix = "datasource.cluster")
    public DataSource cluster() {
//        return DruidDataSourceBuilder.create().build();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(clusterUrl);
        dataSource.setUsername(clusterUsername);
        dataSource.setPassword(clusterPassword);
        dataSource.setDriverClassName(clusterDriverClassName);

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


    /**
     * 初始化xxl库
     *
     * @return
     */
    @Bean(XXL_DATA_SOURCE)
    public DataSource xxl() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(xxlUrl);
        dataSource.setUsername(xxlUsername);
        dataSource.setPassword(xxlPassword);
        dataSource.setDriverClassName(xxlDriverClassName);

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

    /**
     * 动态数据源配置
     *
     * @return
     */
    @Bean
    public DataSource multipleDataSource(@Qualifier(MASTER_DATA_SOURCE) DataSource master,
                                         @Qualifier(CLUSTER_DATA_SOURCE) DataSource cluster,
                                         @Qualifier(XXL_DATA_SOURCE) DataSource xxl) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(MASTER_DATA_SOURCE, master);
        targetDataSources.put(CLUSTER_DATA_SOURCE, cluster);
        targetDataSources.put(XXL_DATA_SOURCE, xxl);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        //设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(master);
        return dynamicDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(multipleDataSource(master(), cluster(), xxl()));
        /**
         * setMapperLocations必须加，解决报错：invalid bound statement (not found)问题
         * 指定mapper所在位置
         */
        sqlSessionFactory.setMapperLocations(CommonUtil.resolveMapperLocations(mapperLocation));
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        //添加分页功能
        sqlSessionFactory.setPlugins(paginationInterceptor());
        return sqlSessionFactory.getObject();
    }

}
