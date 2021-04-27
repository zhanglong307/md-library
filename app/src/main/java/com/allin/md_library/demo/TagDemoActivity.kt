package com.allin.md_library.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.allin.md_library.R
import com.allin.mdlibrary.log.MdLog

class TagDemoActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_demo)


    }

    override fun onClick(v: View?) {

        when(v?.id) {
            R.id.tabBtn ->{
                printLog()
            }
        }
    }

    private fun printLog() {
        MdLog.v("MdLogssss");
    }
}