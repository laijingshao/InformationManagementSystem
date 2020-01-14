package com.pp.IMS.global;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author laijs
 * @date 2019-12-27-21:13
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 获取请求路径
        String path = request.getServletPath();
        // 和登录相关的请求不需要验证，直接放行
        if(path.toLowerCase().indexOf("login") != -1){
            filterChain.doFilter(request,response);
        } else {
            // 从Session域中获取USER数据，有则已登录，无则未登录
            HttpSession session = request.getSession();
            Object user = session.getAttribute("USER");
            if(user != null){
                filterChain.doFilter(request,response);
            } else {
                response.sendRedirect(request.getContextPath()+"/toLogin.do");
            }
        }
    }

    @Override
    public void destroy() {
    }
}
