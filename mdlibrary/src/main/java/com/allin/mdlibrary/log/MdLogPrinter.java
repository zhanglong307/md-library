package com.allin.mdlibrary.log;

import androidx.annotation.NonNull;

public interface MdLogPrinter {
    void print(@NonNull MdLogConfig config, int level, String tag, @NonNull String printString);
}
