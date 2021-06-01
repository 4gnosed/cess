package cn.edu.cess.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(CustomFilter.class);
    private static final List<String> EXCLUDE_URL = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        EXCLUDE_URL.add("");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        log.info("过滤器，客户端host：" + request.getRemoteHost());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
