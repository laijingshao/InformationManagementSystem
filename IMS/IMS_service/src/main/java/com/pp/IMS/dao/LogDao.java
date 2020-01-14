package com.pp.IMS.dao;

import com.pp.IMS.entity.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author laijs
 * @date 2019-12-28-13:51
 */
@Repository("logDao")
public interface LogDao {
    // 添加日志
    public void insert(Log log);
    // 查询日志
    public List<Log> selectByType(String type);
}
