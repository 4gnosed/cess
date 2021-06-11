package cn.edu.cess.config;

import cn.edu.cess.util.CommonUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Package: cn.edu.cess.config
 * @Description:次数据源配置
 * @Author: LuDeSong
 * @Date: 2021-6-10 11:23
 */

@Configuration
@MapperScan(basePackages = {"cn.edu.cess.mapper.admin"},
        sqlSessionTemplateRef = "clusterSqlSessionTemplate")
public class DruidConfigCluster {

    @Autowired
    PaginationInterceptor paginationInterceptor;

    @Value("${mybatis-plus.mapper-locations}")
    private String mapperLocation;


    /**
     * 连接数据库信息 这个其实更好的是用配置中心完成
     */
    @Value("${datasource.cluster.datasource.url}")
    private String url;

    @Value("${datasource.cluster.datasource.username}")
    private String username;

    @Value("${datasource.cluster.datasource.password}")
    private String password;

    @Value("${datasource.cluster.datasource.driverClassName}")
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


    @Bean(name = "clusterDataSource")
    public DataSource clusterDataSource() {
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
    @Bean(name = "clusterTransactionManager")
    public DataSourceTransactionManager clusterTransactionManager() {
        return new DataSourceTransactionManager(clusterDataSource());
    }

    //Mybatis专属配置
//    @Primary
//    @Bean(name = "clusterSqlSessionFactory")
//    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource clusterDataSource)
//            throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(clusterDataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(ClusterDataSourceConfig.MAPPER_LOCATION));
//
//        return sessionFactory.getObject();
//    }

    //Mybatis-plus专属配置
    @Bean("clusterSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(clusterDataSource());

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setLogImpl(StdOutImpl.class);
        factoryBean.setConfiguration(configuration);
        //指定xml路径.
        factoryBean.setMapperLocations(CommonUtil.resolveMapperLocations(mapperLocation));
        //分页插件
        Interceptor[] plugins = {paginationInterceptor};
        factoryBean.setPlugins(plugins);

        return factoryBean.getObject();
    }

    @Bean("clusterSqlSessionTemplate")
    public SqlSessionTemplate clusterSqlSessionTemplate() throws Exception {
        // 使用上面配置的Factory
        SqlSessionTemplate template = new SqlSessionTemplate(clusterSqlSessionFactory());
        return template;
    }
}
