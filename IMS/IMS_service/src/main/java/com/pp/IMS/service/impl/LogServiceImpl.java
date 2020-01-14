package com.pp.IMS.service.impl;

import com.pp.IMS.dao.LogDao;
import com.pp.IMS.entity.Log;
import com.pp.IMS.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author laijs
 * @date 2019-12-28-14:00
 */
@Service("logService")
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public void addSystemLog(Log log) {
        log.setOprTime(new Date());
        log.setType("system");
        logDao.insert(log);
    }

    @Override
    public void addLoginLog(Log log) {
        log.setOprTime(new Date());
        log.setType("login");
        logDao.insert(log);
    }

    @Override
    public void addOperationLog(Log log) {
        log.setOprTime(new Date());
        log.setType("operation");
        logDao.insert(log);
    }

    @Override
    public List<Log> getSystemLog() {
        List<Log> logs = logDao.selectByType("system");
        return logs;
    }

    @Override
    public List<Log> getLoginLog() {
        List<Log> logs = logDao.selectByType("login");
        return logs;
    }

    @Override
    public List<Log> getOperationLog() {
        List<Log> logs = logDao.selectByType("operation");
        return logs;
    }
}
