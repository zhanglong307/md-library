package com.allin.mdlibrary.log;

public class MdThreadFormatter implements MdLogFormatter<Thread> {
    @Override
    public String format(Thread data) {
        return "Thread:" + data.getName();
    }
}
