package cn.edu.cess.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(CustomFilter.class);
    private static final List<String> EXCLUDE_URL = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        EXCLUDE_URL.add("/one-front/backstage/mealTimes/getUser");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
