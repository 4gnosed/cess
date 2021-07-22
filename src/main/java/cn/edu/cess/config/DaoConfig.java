//package cn.edu.cess.config;
//
//
//import cn.edu.cess.mapper.base.BaseDao;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class DaoConfig {
//
//    @Qualifier("xxlSqlSessionFactory")
//    SqlSessionFactory xxlSqlSessionFactory;
//
//    @Bean(name = "baseDaoXxl")
//    public BaseDao baseDaoXxl() throws Exception {
//        BaseDao baseDao = new BaseDao();
//        baseDao.setSqlSessionFactory(xxlSqlSessionFactory);
//        return baseDao;
//    }
//
//
//}
