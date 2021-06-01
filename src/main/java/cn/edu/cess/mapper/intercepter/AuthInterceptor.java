package cn.edu.cess.mapper.intercepter;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Properties;

@Component
@Intercepts({
        @Signature(method = "query", type = Executor.class, args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class})})
public class AuthInterceptor implements Interceptor {

    protected Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("进入拦截............");

        if (invocation.getTarget() instanceof StatementHandler) {
            //先拦截到StatementHandler
            StatementHandler delegate = (StatementHandler) invocation.getTarget();
            //拿到参数和sql串等信息
            BoundSql boundSql = delegate.getBoundSql();
            String sql = boundSql.getSql();
            Object parameterObject = boundSql.getParameterObject();
            log.info("查询sql：" + sql);
            log.info("查询sql参数：{}", JSON.toJSONString(parameterObject));
        }
        return invocation.proceed();
    }


    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
    }


}
