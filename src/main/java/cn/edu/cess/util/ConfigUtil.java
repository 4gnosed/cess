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

    public static int getIntProperty(String key) {
        return Integer.parseInt(localEnv.getProperty(key));
    }


    public static String getProperty(String key) {
        return localEnv.getProperty(key);
    }

    public static String getProperty(String key, String def) {
        if (StringUtils.isBlank(localEnv.getProperty(key))) {
            return def;
        }
        return localEnv.getProperty(key);
    }

}
