package com.pp.IMS.service;

import com.pp.IMS.entity.Staff;

/**
 * @author laijs
 * @date 2019-12-27-20:11
 */
public interface SelfService {
    // 登录
    public Staff login(String account,String password);
    // 修改密码
    public void changePassword(int id,String password);
}
