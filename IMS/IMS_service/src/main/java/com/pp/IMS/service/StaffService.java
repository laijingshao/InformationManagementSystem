package com.pp.IMS.service;

import com.pp.IMS.entity.Department;
import com.pp.IMS.entity.Staff;

import java.util.List;

/**
 * @author laijs
 * @date 2019-12-27-16:23
 */
public interface StaffService {
    // 添加员工
    public void add(Staff staff);
    // 根据员工ID删除员工
    public void remove(int id);
    // 修改员工信息
    public void edit(Staff staff);
    // 根据员工ID查询员工
    public Staff findById(int id);
    // 查询所有员工
    public List<Staff> findAll();
}
