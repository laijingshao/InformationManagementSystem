package com.pp.IMS.service.impl;

import com.pp.IMS.dao.StaffDao;
import com.pp.IMS.entity.Staff;
import com.pp.IMS.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author laijs
 * @date 2019-12-27-16:25
 */
@Service("staffServiceImpl")
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;

    public void add(Staff staff) {
        // 添加员工时设置一些默认的信息
        staff.setPassword("123456");
        staff.setWorkTime(new Date());
        staff.setStatus("正常");
        staffDao.insert(staff);
    }

    public void remove(int id) {
        staffDao.delete(id);
    }

    public void edit(Staff staff) {
        staffDao.update(staff);
    }

    public Staff findById(int id) {
        return staffDao.selectById(id);
    }

    public List<Staff> findAll() {
        return staffDao.selectAll();
    }
}
