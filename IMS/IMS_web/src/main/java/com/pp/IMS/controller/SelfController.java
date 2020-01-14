package com.pp.IMS.controller;

import com.pp.IMS.entity.Staff;
import com.pp.IMS.service.SelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author laijs
 * @date 2019-12-27-20:52
 */
@Controller("selfController")
public class SelfController {
    @Autowired
    private SelfService selfService;

    // 跳转到登录页面，避免直接访问登录页面，项目访问：/toLogin.do
    public void toLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }

    // 获取login.jsp页面传递的账户和密码，控制登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        Staff staff = selfService.login(account, password);
        if(staff == null){
            // 登录失败，直接重定向到toLogin控制器
            response.sendRedirect("toLogin.do");
        } else {
            // 登陆成功，将用户存在Session域中，重定向到主界面
            HttpSession session = request.getSession();
            session.setAttribute("USER",staff);
            response.sendRedirect("main.do");
        }
    }

    // 退出：清除session域中的用户信息
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("USER",null);
        response.sendRedirect("toLogin.do");
    }

    // 跳转到主界面
    public void main(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    // 跳转到个人信息界面
    public void info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../self_info.jsp").forward(request,response);
    }

    // 跳转到修改密码界面
    public void toChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../self_changePassword.jsp").forward(request,response);
    }

    // 处理修改密码操作
    public void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        HttpSession session = request.getSession();
        Staff staff = (Staff) session.getAttribute("USER");
        // 密码不正确
        if(!staff.getPassword().equals(password)){
            session.setAttribute("msg","你输入的密码不正确，请重新输入！！");
            session.setAttribute("msg2",null);
            response.sendRedirect("toChangePassword.do");
        } else {
            // 判断两次输入的密码是否正确
            if(!password1.equals(password2)){
                session.setAttribute("msg",null);
                session.setAttribute("msg2","两次输入密码不同，请正确输入！！");
                response.sendRedirect("toChangePassword.do");
            } else {
                session.setAttribute("msg",null);
                session.setAttribute("msg2",null);
                selfService.changePassword(staff.getId(),password1);
                // 返回登录界面（主界面跳转）
                response.getWriter().print("<script type=\"text/javascript\">parent.location.href=\"../logout.do\"</script>");
            }
        }
    }

}
