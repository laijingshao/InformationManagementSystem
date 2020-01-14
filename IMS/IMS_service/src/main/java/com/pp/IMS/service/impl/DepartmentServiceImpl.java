package com.pp.IMS.service.impl;

import com.pp.IMS.dao.DepartmentDao;
import com.pp.IMS.entity.Department;
import com.pp.IMS.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author laijs
 * @date 2019-12-26-21:27
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    public void add(Department department) {
        departmentDao.insert(department);
    }

    public void remove(int id) {
        departmentDao.delete(id);
    }

    public void edit(Department department) {
        departmentDao.update(department);
    }

    public Department findById(int id) {
        Department department = departmentDao.selectById(id);
        return department;
    }

    public List<Department> findAll() {
        List<Department> departments = departmentDao.selectAll();
        return departments;
    }
}
