package com.pp.IMS.service;

import com.pp.IMS.entity.Log;

import java.util.List;

/**
 * @author laijs
 * @date 2019-12-28-13:59
 */
public interface LogService {
    void addSystemLog(Log log);
    void addLoginLog(Log log);
    void addOperationLog(Log log);

    List<Log> getSystemLog();
    List<Log> getLoginLog();
    List<Log> getOperationLog();
}
