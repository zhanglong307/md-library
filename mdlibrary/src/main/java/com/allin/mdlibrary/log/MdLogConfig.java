package com.allin.mdlibrary.log;

public abstract class MdLogConfig {
    static int MAX_LEN = 512;
    static MdThreadFormatter HI_THREAD_FORMATTER = new MdThreadFormatter();
    static MdStackTraceFormatter HI_STACK_TRACE_FORMATTER = new MdStackTraceFormatter();

    public JsonParser injectJsonParser() {
        return null;
    }

    public String getGlobalTag() {
        return "MdLog";
    }

    public boolean enable() {
        return true;
    }

    public boolean includeThread() {
        return false;
    }

    public int stackTraceDepth() {
        return 5;
    }

    public MdLogPrinter[] printers() {
        return null;
    }

    public interface JsonParser {
        String toJson(Object src);
    }
}
