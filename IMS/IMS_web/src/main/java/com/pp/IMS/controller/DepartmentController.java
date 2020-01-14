package com.pp.IMS.controller;

import com.pp.IMS.entity.Department;
import com.pp.IMS.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author laijs
 * @date 2019-12-26-22:25
 */
@Controller("departmentController")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // 将所有部门信息显示到department_list.jsp页面
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> list = departmentService.findAll();
        request.setAttribute("LIST",list);
        request.getRequestDispatcher("../department_list.jsp").forward(request,response);
    }

    // 转到添加页面department_add.jsp
    public void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../department_add.jsp").forward(request,response);
    }

    // 从department_add.jsp页面获取部门信息添加部门，重定向到list方法
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        Department department = new Department();
        department.setName(name);
        department.setAddress(address);
        departmentService.add(department);
        response.sendRedirect("list.do");
    }

    // 转到修改页面department_edit.jsp(携带原部门信息)
    public void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Department department = departmentService.findById(id);
        request.setAttribute("department",department);
        request.getRequestDispatcher("../department_edit.jsp").forward(request,response);
    }

    // 从department_edit.jsp页面获取部门信息修改部门，重定向到list方法
    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        Department department = departmentService.findById(id);
        department.setName(name);
        department.setAddress(address);
        departmentService.edit(department);
        response.sendRedirect("list.do");
    }

    // 删除
    public void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        departmentService.remove(id);
        response.sendRedirect("list.do");
    }
}
