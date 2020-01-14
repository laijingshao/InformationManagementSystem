package com.pp.IMS.controller;

import com.pp.IMS.entity.Department;
import com.pp.IMS.entity.Staff;
import com.pp.IMS.service.DepartmentService;
import com.pp.IMS.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author laijs
 * @date 2019-12-26-22:25
 */
@Controller("staffController")
public class StaffController {

    @Autowired
    private StaffService staffService;

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Staff> list = staffService.findAll();
        request.setAttribute("LIST",list);
        request.getRequestDispatcher("../staff_list.jsp").forward(request,response);
    }

    // 在添加和修改员工页面上需要使用部门信息
    @Autowired
    private DepartmentService departmentService;

    public void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> list = departmentService.findAll();
        request.setAttribute("DLIST",list);
        request.getRequestDispatcher("../staff_add.jsp").forward(request,response);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        int did = Integer.parseInt(request.getParameter("did"));
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String idNumber = request.getParameter("idNumber");
        Date bornDate = null;
        try {
            bornDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("bornDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String info = request.getParameter("info");

        Staff staff = new Staff();
        staff.setAccount(account);
        staff.setDid(did);
        staff.setName(name);
        staff.setSex(sex);
        staff.setIdNumber(idNumber);
        staff.setBornDate(bornDate);
        staff.setInfo(info);

        staffService.add(staff);
        response.sendRedirect("list.do");
    }

    public void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Staff staff = staffService.findById(id);
        request.setAttribute("staff",staff);
        List<Department> list = departmentService.findAll();
        request.setAttribute("DLIST",list);
        request.getRequestDispatcher("../staff_edit.jsp").forward(request,response);
    }

    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String account = request.getParameter("account");
        int did = Integer.parseInt(request.getParameter("did"));
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String idNumber = request.getParameter("idNumber");
        Date bornDate = null;
        try {
            bornDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("bornDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String info = request.getParameter("info");

        Staff staff = staffService.findById(id);
        staff.setAccount(account);
        staff.setDid(did);
        staff.setName(name);
        staff.setSex(sex);
        staff.setIdNumber(idNumber);
        staff.setBornDate(bornDate);
        staff.setInfo(info);

        staffService.edit(staff);
        response.sendRedirect("list.do");
    }

    public void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        staffService.remove(id);
        response.sendRedirect("list.do");
    }

    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Staff staff = staffService.findById(id);
        request.setAttribute("staff",staff);
        request.getRequestDispatcher("../staff_detail.jsp").forward(request,response);
    }
}
