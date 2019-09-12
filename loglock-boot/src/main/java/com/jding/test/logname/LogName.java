package com.jding.test.logname;

public enum  LogName {

    SYSTEM_LOG("systemLog"),
    OPO_LOG("operatorLog");

    private String logFileName;

    LogName(String logName) {
        this.logFileName = logName;
    }

    public String getLogName() {
        return logFileName;
    }

    public void setLogName(String logName) {
        this.logFileName = logName;
    }
}
