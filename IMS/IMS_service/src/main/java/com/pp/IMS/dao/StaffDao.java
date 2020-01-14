package com.pp.IMS.dao;

import com.pp.IMS.entity.Department;
import com.pp.IMS.entity.Staff;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author laijs
 * @date 2019-12-27-16:10
 */
@Repository("staffDao")
public interface StaffDao {
    // 增
    public void insert(Staff staff);
    // 删
    public void delete(int id);
    // 改
    public void update(Staff staff);
    // 查
    public Staff selectById(int id);
    public List<Staff> selectAll();
}
