package com.pp.IMS.dao;

import com.pp.IMS.entity.Staff;
import org.springframework.stereotype.Repository;

/**
 * @author laijs
 * @date 2019-12-27-20:07
 */
@Repository("selfDao")
public interface SelfDao {
    public Staff selectByAccount(String account);
}
