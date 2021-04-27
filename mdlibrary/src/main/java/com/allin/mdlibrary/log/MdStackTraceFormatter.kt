package com.allin.mdlibrary.log

/**
 * 堆栈信息格式化
 */
class MdStackTraceFormatter : MdLogFormatter<Array<StackTraceElement?>?> {
    override fun format(stackTrace: Array<StackTraceElement?>?): String? {
        val sb = StringBuilder(128)
        return if (stackTrace == null || stackTrace.isEmpty()) {
            ""
        } else if (stackTrace.size == 1) {
            "\t─ " + stackTrace[0].toString()
        } else {
            var i = 0
            val len = stackTrace.size
            while (i < len) {
                if (i == 0) {
                    sb.append("stackTrace:  \n")
                }
                if (i != len - 1) {
                    sb.append("\t├ ")
                    sb.append(stackTrace[i].toString())
                    sb.append("\n")
                } else {
                    sb.append("\t└ ")
                    sb.append(stackTrace[i].toString())
                }
                i++
            }
            sb.toString()
        }
    }

}