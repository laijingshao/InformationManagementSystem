package com.pp.IMS.global;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author laijs
 * @date 2019-12-26-17:52
 */
public class EncodingFilter implements Filter {

    private String encoding = "UTF-8";

    public void init(FilterConfig filterConfig) throws ServletException {
        if (filterConfig.getInitParameter("ENCODING") != null){
            encoding = filterConfig.getInitParameter("ENCODING");
        }
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {
        encoding = null;
    }
}
