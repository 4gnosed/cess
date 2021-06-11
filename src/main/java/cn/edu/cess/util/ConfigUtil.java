package cn.edu.cess.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ConfigUtil {

    @Autowired
    private Environment env;

    private static Environment localEnv;

    @PostConstruct
    public void init() {
        localEnv = this.env;

    }

    public static Integer getIntProperty(String key) {
        if(localEnv==null) return null;
        String property = localEnv.getProperty(key);
        return property == null ? null : Integer.parseInt(property);
    }


    public static String getProperty(String key) {
        if(localEnv==null) return null;
        return localEnv.getProperty(key);
    }

    public static String getProperty(String key, String def) {
        if(localEnv==null) return null;
        if (StringUtils.isBlank(localEnv.getProperty(key))) {
            return def;
        }
        return localEnv.getProperty(key);
    }

}
