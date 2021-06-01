package cn.edu.cess.interceptor;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author Gnosed Lu
 * @Date 2020/6/3
 * @Description 自定义ID生成器
 */
@Slf4j
public class CustomIdGenerator implements IdentifierGenerator {
    private final AtomicLong al = new AtomicLong(1);

    @Override
    public Number nextId(Object entity) {
        //可以将当前传入的class全类名来作为bizKey,或者提取参数来生成bizKey进行分布式Id调用生成.
        String bizKey = entity.getClass().getName();
//        log.info("bizKey:{}", bizKey);
//        MetaObject metaObject = SystemMetaObject.forObject(entity);
//        String name = (String) metaObject.getValue("name");
        final long id = al.getAndAdd(1);
//        log.info("为{}生成主键值->:{}", name, id);
        return id;
    }
}
