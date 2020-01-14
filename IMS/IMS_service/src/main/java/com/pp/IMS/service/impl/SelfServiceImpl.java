package com.pp.IMS.service.impl;

import com.pp.IMS.dao.SelfDao;
import com.pp.IMS.dao.StaffDao;
import com.pp.IMS.entity.Staff;
import com.pp.IMS.service.SelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author laijs
 * @date 2019-12-27-20:14
 */
@Service("selfService")
public class SelfServiceImpl implements SelfService {
    @Autowired
    private SelfDao selfDao;

    @Autowired
    private StaffDao staffDao;

    @Override
    public Staff login(String account, String password) {
        Staff staff = selfDao.selectByAccount(account);
        // 账户不存在，登录失败
        if(staff == null)return null;
        // 账户存在，判断密码，密码匹配，登录成功
        if(staff.getPassword().equals(password)) return staff;
        // 密码不匹配，登录失败
        return null;
    }

    @Override
    public void changePassword(int id, String password) {
        Staff staff = staffDao.selectById(id);
        staff.setPassword(password);
        staffDao.update(staff);
    }
}
