package com.allin.mdlibrary.log;

import android.util.Log;

import androidx.annotation.NonNull;


public class MdConsolePrinter implements MdLogPrinter {

    @Override
    public void print(@NonNull MdLogConfig config, int level, String tag, @NonNull String printString) {
        int len = printString.length();
        int countOfSub = len / MdLogConfig.MAX_LEN;
        if (countOfSub > 0) {
            StringBuilder log = new StringBuilder();
            int index = 0;
            for (int i = 0; i < countOfSub; i++) {
                log.append(printString.substring(index, index + MdLogConfig.MAX_LEN));
                index += MdLogConfig.MAX_LEN;
            }
            if (index != len) {
                log.append(printString.substring(index, len));
            }
            Log.println(level, tag, log.toString());
        } else {
            Log.println(level, tag, printString);
        }
    }
}
