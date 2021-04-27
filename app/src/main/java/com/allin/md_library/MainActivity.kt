package com.allin.md_library

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.allin.md_library.demo.TagDemoActivity

class MainActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(v: View?) {

        when(v!!.id) {
            R.id.tabBtn ->{
                startActivity(Intent(this, TagDemoActivity::class.java))
            }
        }
    }
}