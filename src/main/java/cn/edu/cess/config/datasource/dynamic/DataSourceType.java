package cn.edu.cess.config.datasource.dynamic;


import cn.edu.cess.config.datasource.MybatisPlusConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 切换数据源注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DataSourceType {
    //数据源名称
    String value() default MybatisPlusConfig.MASTER_DATA_SOURCE;
}
