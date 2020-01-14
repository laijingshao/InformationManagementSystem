package com.pp.IMS.global;

import com.pp.IMS.entity.Log;
import com.pp.IMS.entity.Staff;
import com.pp.IMS.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author laijs
 * @date 2019-12-28-14:35
 */
@Component
@Aspect
public class LogAdvice {

    @Autowired
    private LogService logService;

    // 操作日志 - 关于控制器的后置通知，切点是DepartmentController和StaffController控制器中的方法（toAdd和add重复）
    @AfterReturning("execution(* com.pp.IMS.controller.*.*(..)) && !execution(* com.pp.IMS.controller.SelfController.*(..))" +
            " && !execution(* com.pp.IMS.controller.*.to*(..))")
    public void operationLog(JoinPoint joinPoint){
        Log log = new Log();
        // 切点的类名（控制器）表示模块
        log.setMoudle(joinPoint.getTarget().getClass().getSimpleName());
        // 切点的方法名表示操作类型
        log.setOperation(joinPoint.getSignature().getName());
        // 在request对象中有操作者的信息(切点方法的第一个参数就是request对象)
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();
        Staff staff = (Staff) session.getAttribute("USER");
        log.setOperator(staff.getAccount());
        // 无异常代表操作成功
        log.setResult("操作成功");
        logService.addOperationLog(log);
    }

    // 系统日志 - 关于控制器的异常通知，切点同上（不需考虑重复方法）
    @AfterThrowing(throwing = "e",pointcut = "execution(* com.pp.IMS.controller.*.*(..)) && !execution(* com.pp.IMS.controller.SelfController.*(..))")
    public void systemLog(JoinPoint joinPoint,Throwable e){
        Log log = new Log();
        log.setMoudle(joinPoint.getTarget().getClass().getSimpleName());
        log.setOperation(joinPoint.getSignature().getName());
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();
        Staff staff = (Staff) session.getAttribute("USER");
        log.setOperator(staff.getAccount());
        // 操作结果为异常类型
        log.setResult(e.getClass().getSimpleName());
        logService.addSystemLog(log);
    }

    // 登录日志 - 关于登录控制器中登录最终通知和退出的前置通知，切点为SelfController控制器中的login和logout方法
    @After("execution(* com.pp.IMS.controller.SelfController.login(..))")
    public void loginLog(JoinPoint joinPoint){
        log(joinPoint);
    }
    @Before("execution(* com.pp.IMS.controller.SelfController.logout(..))")
    public void logoutLog(JoinPoint joinPoint){
        log(joinPoint);
    }
    public void log(JoinPoint joinPoint){
        Log log = new Log();
        log.setMoudle(joinPoint.getTarget().getClass().getSimpleName());
        log.setOperation(joinPoint.getSignature().getName());
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();
        Object user = session.getAttribute("USER");
        if(user == null){
            // 登录不成功，从登录页面获取的账号信息
            log.setOperator(request.getParameter("account"));
            log.setResult("登录失败");
        } else {
            Staff staff = (Staff) user;
            // 登录成功，获取退出（退出操作，staff一定不为空）
            log.setOperator(staff.getAccount());
            log.setResult("成功");
        }
        logService.addLoginLog(log);
    }
}
