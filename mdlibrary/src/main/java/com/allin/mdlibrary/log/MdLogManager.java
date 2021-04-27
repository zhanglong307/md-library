package com.allin.mdlibrary.log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MdLogManager {
    private MdLogConfig config;
    private static MdLogManager instance;
    private List<MdLogPrinter> printers = new ArrayList<>();

    private MdLogManager(MdLogConfig config, MdLogPrinter[] printers) {
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static MdLogManager getInstance() {
        return instance;
    }

    public static void init(@NonNull MdLogConfig config, MdLogPrinter... printers) {
        instance = new MdLogManager(config, printers);
    }

    public MdLogConfig getConfig() {
        return config;
    }

    public List<MdLogPrinter> getPrinters() {
        return printers;
    }

    public void addPrinter(MdLogPrinter printer) {
        printers.add(printer);
    }

    public void removePrinter(MdLogPrinter printer) {
        if (printers != null) {
            printers.remove(printer);
        }
    }
}
