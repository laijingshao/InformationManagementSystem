package com.pp.IMS.global;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author laijs
 * @date 2019-12-26-18:50
 */
public class DispatcherServlet extends HttpServlet {
    // 避免每次请求都要加载配置文件，将其抽取
    private ApplicationContext context;
    // 并在核心控制器对象创建时初始化
    @Override
    public void init() throws ServletException {
        super.init();
        context = new ClassPathXmlApplicationContext("spring.xml");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求路径，解析请求，分配对应的控制器
        String path = request.getServletPath().substring(1);
        String beanName = null;
        String methodName = null;
        int index = path.indexOf("/");
        if(path.indexOf("/") != -1){
            beanName = path.substring(0,index)+"Controller";
            methodName = path.substring(index+1,path.indexOf(".do"));
        } else {
            beanName = "selfController";
            methodName = path.substring(0,path.indexOf(".do"));
        }
        // 加载配置文件
 //       ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        // 根据控制器的名创建控制器
        Object bean = context.getBean(beanName);
        try {
            // 利用反射，根据控制器和方法名获取方法对象
            Method method = bean.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 执行方法
            method.invoke(bean,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
