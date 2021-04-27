package com.allin.mdlibrary.log;

public interface MdLogFormatter<T> {

    String format(T data);
}