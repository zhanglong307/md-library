package com.allin.mdlibrary.log;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

/**
 * Tips:
 * 1. 打印堆栈信息
 * 2. File输出
 * 3. 模拟控制台
 */
public class MdLog {
    private static final String MD_LOG_PACKAGE;

    static {
        String className = MdLog.class.getName();
        MD_LOG_PACKAGE = className.substring(0, className.lastIndexOf('.') + 1);
    }

    public static void v(Object... contents) {
        log(MdLogType.V, contents);
    }

    public static void vt(String tag, Object... contents) {
        log(MdLogType.V, tag, contents);
    }

    public static void d(Object... contents) {
        log(MdLogType.D, contents);
    }

    public static void dt(String tag, Object... contents) {
        log(MdLogType.D, tag, contents);
    }

    public static void i(Object... contents) {
        log(MdLogType.I, contents);
    }

    public static void it(String tag, Object... contents) {
        log(MdLogType.I, tag, contents);
    }

    public static void w(Object... contents) {
        log(MdLogType.W, contents);
    }

    public static void wt(String tag, Object... contents) {
        log(MdLogType.W, tag, contents);
    }

    public static void e(Object... contents) {
        log(MdLogType.E, contents);
    }

    public static void et(String tag, Object... contents) {
        log(MdLogType.E, tag, contents);
    }

    public static void a(Object... contents) {
        log(MdLogType.A, contents);
    }

    public static void at(String tag, Object... contents) {
        log(MdLogType.A, tag, contents);
    }

    public static void log(@MdLogType.TYPE int type, Object... contents) {
        log(type, MdLogManager.getInstance().getConfig().getGlobalTag(), contents);
    }

    public static void log(@MdLogType.TYPE int type, @NonNull String tag, Object... contents) {
        log(MdLogManager.getInstance().getConfig(), type, tag, contents);
    }

    public static void log(@NonNull MdLogConfig config, @MdLogType.TYPE int type, @NonNull String tag, Object... contents) {
        if (!config.enable()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (config.includeThread()) {
            String threadInfo = MdLogConfig.HI_THREAD_FORMATTER.format(Thread.currentThread());
            sb.append(threadInfo).append("\n");
        }
        if (config.stackTraceDepth() > 0) {
            String stackTrace = MdLogConfig.HI_STACK_TRACE_FORMATTER.format(
                    MdStackTraceUtil.getCroppedRealStackTrack(new Throwable().getStackTrace(), MD_LOG_PACKAGE, config.stackTraceDepth()));
            sb.append(stackTrace).append("\n");
        }
        String body = parseBody(contents, config);
        if (body != null) {//替换转义字符\
            body = body.replace("\\\"", "\"");
        }
        sb.append(body);
        List<MdLogPrinter> printers =
                config.printers() != null ? Arrays.asList(config.printers()) : MdLogManager.getInstance().getPrinters();
        if (printers == null) {
            return;
        }
        //打印log
        for (MdLogPrinter printer : printers) {
            printer.print(config, type, tag, sb.toString());
        }
    }


    private static String parseBody(@NonNull Object[] contents, @NonNull MdLogConfig config) {
        if (config.injectJsonParser() != null) {
            //只有一个数据且为String的情况下不再进行序列化
            if (contents.length == 1 && contents[0] instanceof String) {
                return (String) contents[0];
            }
            return config.injectJsonParser().toJson(contents);
        }
        StringBuilder sb = new StringBuilder();
        for (Object o : contents) {
            sb.append(o.toString()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
