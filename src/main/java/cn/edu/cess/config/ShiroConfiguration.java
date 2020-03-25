package cn.edu.cess.config;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.filter.URLPathMatchingFilter;
import cn.edu.cess.realm.CustomAuthorizingRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author Gnosed Lu
 * @Date 2020/1/16
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getRealm());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * 启用 rememberMe 机制保持登录状态
     *
     * @return
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Constant.GNOSED_CIPHER_KEY.getBytes());
        return cookieRememberMeManager;
    }

    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie(Constant.REMEMBER_ME);
        simpleCookie.setMaxAge(Constant.MAX_AGE);
        return simpleCookie;
    }

    @Bean
    public CustomAuthorizingRealm getRealm() {
        CustomAuthorizingRealm realm = new CustomAuthorizingRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(Constant.MD_5);
        hashedCredentialsMatcher.setHashIterations(Constant.HASH_ITERATIONS);
        return hashedCredentialsMatcher;
    }

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * Filter Chain定义说明
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/nowhere");

        Map<String, Filter> customizedFilter = new HashMap<>();  // 自定义过滤器设置 1
        customizedFilter.put("customizedFilter", getURLPathMatchingFilter()); // 自定义过滤器设置 2，命名，需在设置过滤路径前

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//        filterChainDefinitionMap.put("/api/authentication", "authc"); // 防鸡贼登录，暂时不需要
        filterChainDefinitionMap.put("/api/menu", "authc");
        filterChainDefinitionMap.put("/api/admin/**", "authc");

        filterChainDefinitionMap.put("/api/admin/**", "customizedFilter");  // 自定义过滤器设置 3，设置过滤路径

        shiroFilterFactoryBean.setFilters(customizedFilter); // 自定义过滤器设置 4，启用
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    public URLPathMatchingFilter getURLPathMatchingFilter() {
        return new URLPathMatchingFilter();
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}


