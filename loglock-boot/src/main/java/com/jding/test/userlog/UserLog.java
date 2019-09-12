package com.jding.test.userlog;

import com.jding.test.logname.LogName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

public class UserLog {
    public static Logger SystemLog() {
        return getLogger(LogName.SYSTEM_LOG);
    }

    public static Logger OpoLog() {
        return getLogger(LogName.OPO_LOG);
    }

    private static Logger getLogger(LogName logName) {
        return LoggerFactory.getLogger(logName.getLogName());
    }


}
