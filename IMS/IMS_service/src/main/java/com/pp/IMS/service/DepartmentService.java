package com.pp.IMS.service;

import com.pp.IMS.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author laijs
 * @date 2019-12-26-21:23
 */
public interface DepartmentService {
    // 添加部门
    public void add(Department department);
    // 根据部门ID删除部门
    public void remove(int id);
    // 修改部门信息
    public void edit(Department department);
    // 根据部门ID查询部门
    public Department findById(int id);
    // 查询所有部门
    public List<Department> findAll();
}
