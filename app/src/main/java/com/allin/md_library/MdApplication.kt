package com.allin.md_library

import android.app.Application
import com.allin.mdlibrary.log.MdConsolePrinter
import com.allin.mdlibrary.log.MdFilePrinter
import com.allin.mdlibrary.log.MdLogConfig
import com.allin.mdlibrary.log.MdLogManager
import com.google.gson.Gson

class MdApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        MdLogManager.init(object : MdLogConfig() {
            override fun injectJsonParser(): JsonParser {
                return JsonParser { src -> Gson().toJson(src) }
            }

            override fun getGlobalTag(): String {
                return "Md-Log"
            }

            override fun includeThread(): Boolean {
                return true
            }

            override fun stackTraceDepth(): Int {
                return 5
            }
        },
                MdConsolePrinter(),
                MdFilePrinter.getInstance(applicationContext.cacheDir.absolutePath, 0)
        )
    }
}