package com.pp.IMS.dao;

import com.pp.IMS.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author laijs
 * @date 2019-12-26-21:02
 */
@Repository("departmentDao")
public interface DepartmentDao {
    // 增
    public void insert(Department department);
    // 删
    public void delete(int id);
    // 改
    public void update(Department department);
    // 查
    public Department selectById(int id);
    public List<Department> selectAll();
}
