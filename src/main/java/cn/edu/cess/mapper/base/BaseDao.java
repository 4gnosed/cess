package cn.edu.cess.mapper.base;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

//@Repository
@SuppressWarnings("unchecked")
public class BaseDao extends SqlSessionDaoSupport {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    @Resource(name = "sqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }


    /**
     * 根据数据库ID获取唯一记录
     *
     * @param key mapper中sql语句对应的ID
     * @param id  主键
     * @return 唯一记录
     */
    public <T> T getById(String key, Serializable id) {
        long beginTime = System.currentTimeMillis();
        log.info("=================开始请求执行数据库，【入参={}】", "key = " + key + " , id = " + id);
        T t = getSqlSession().selectOne(key, id);
        log.info("=================结束请求执行数据库，【耗时={}】，【返回数据={}】", (System.currentTimeMillis() - beginTime), JSON
                .toJSONString(t));
        return t;
    }

    /**
     * 插入新记录到数据库
     *
     * @param key    mapper中sql语句对应的ID
     * @param params 数据库字段数据
     */
    public int insert(String key, Object params) {
        long beginTime = System.currentTimeMillis();
        log.info("=================开始请求执行数据库，【入参={}】", "key = " + key + " , params = " + JSON.toJSONString(params));
        int t = getSqlSession().insert(key, params);
        log.info("=================结束请求执行数据库，【耗时={}】，【返回数据={}】", (System.currentTimeMillis() - beginTime), t);
        return t;
    }

    /**
     * 插入新记录到数据库
     *
     * @param key    mapper中sql语句对应的ID
     * @param params 数据库字段数据，生成的sql_map中有现成的更新语句可以自动判断为空的值，跟据主ID进行少量参数修改。
     *               这样可以少传参数提高开发效率和性能
     */
    public int update(String key, Object params) {
        long beginTime = System.currentTimeMillis();
        log.info("=================开始请求执行数据库，【入参={}】", "key = " + key + " , params = " + JSON.toJSONString(params));
        int t = getSqlSession().update(key, params);
        log.info("=================结束请求执行数据库，【耗时={}】，【返回数据={}】", (System.currentTimeMillis() - beginTime), t);
        return t;

    }

    /**
     * 根据条件查询唯一记录，如根据ID查询记录详情等操作
     *
     * @param key    mapper中sql语句对应的ID
     * @param params 查询参数
     * @return 唯一记录
     */
    public <T> T getOne(String key, Object params) {
        long beginTime = System.currentTimeMillis();
        log.info("=================开始请求执行数据库，【入参={}】", "key = " + key + " , params = " + JSON.toJSONString(params));
        T t = getSqlSession().selectOne(key, params);
        log.info("=================结束请求执行数据库，【耗时={}】，【返回数据={}】", (System.currentTimeMillis() - beginTime), JSON
                .toJSONString(t));
        return t;
    }

    /**
     * 获取多条数据
     *
     * @param key    mapper中sql语句对应的ID
     * @param params 查询参数
     * @return 多条数据集
     * @author dongjie.wang
     * @date 2012-5-18 上午11:20:58
     */
    public <T> List<T> getList(String key, Object params) {
        long beginTime = System.currentTimeMillis();
        log.info("=================开始请求执行数据库，【入参={}】", "key = " + key + " , params = " + JSON.toJSONString(params));
        List<T> t = getSqlSession().selectList(key, params);
        log.info("=================结束请求执行数据库，【耗时={}】，【返回数据={}】", (System.currentTimeMillis() - beginTime), JSON
                .toJSONString(t));
        return t;

    }

    /**
     * 无参数获取多条数据
     *
     * @param key mapper中sql语句对应的ID
     * @return 多条数据集
     * @author dongjie.wang
     * @date 2012-5-18 上午11:20:58
     */
    public <T> List<T> getList(String key) {
        long beginTime = System.currentTimeMillis();
        log.info("=================开始请求执行数据库，【入参={}】", "key = " + key);
        List<T> t = getSqlSession().selectList(key);
        log.info("=================结束请求执行数据库，【耗时={}】，【返回数据={}】", (System.currentTimeMillis() - beginTime), JSON
                .toJSONString(t));
        return t;

    }

    /**
     * 根据数据库主键ID删除数据库记录
     *
     * @param key mapper中sql语句对应的ID
     * @param id  数据库主键
     */
    public int delete(String key, Serializable id) {
        long beginTime = System.currentTimeMillis();
        log.info("=================开始请求执行数据库，【入参={}】", "key = " + key + " , id = " + id);
        int t = getSqlSession().delete(key, id);
        log.info("=================结束请求执行数据库，【耗时={}】，【返回数据={}】", (System.currentTimeMillis() - beginTime), t);
        return t;
    }

    /**
     * 根据参数删除数据库记录
     *
     * @param key    mapper中sql语句对应的ID
     * @param params 参数
     */
    public int deleteByParams(String key, Object params) {
        long beginTime = System.currentTimeMillis();
        log.info("=================开始请求执行数据库，【入参={}】", "key = " + key + " , params = " + JSON.toJSONString(params));
        int t = getSqlSession().delete(key, params);
        log.info("=================结束请求执行数据库，【耗时={}】，【返回数据={}】", (System.currentTimeMillis() - beginTime), t);
        return t;
    }

    /**
     * 分页查询
     *
     * @param statementId Mapper id
     * @param pageInfo    分页请求
     * @param param       参数
     * @return
     */
    public <T> PageInfo<T> getPage(String statementId, Page pageInfo, Object param) {

        long beginTime = System.currentTimeMillis();
        log.info("=================开始请求执行数据库，【入参={}】", "statementId = " + statementId + " , pageInfo = " + JSON.toJSONString(pageInfo) + " , param = " + JSON.toJSONString(param));
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        PageInfo<T> page = new PageInfo(getSqlSession().selectList(statementId, param));
        log.info("=================结束请求执行数据库，【耗时={}】，【返回数据={}】", (System.currentTimeMillis() - beginTime), JSON
                .toJSONString(page));
        return page;
    }


}
