package com.example.ncca

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar!!.hide()
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)
        val t = Timer()
        val t1: TimerTask = object : TimerTask() {
            override fun run() {
                val i = Intent(this@MainActivity, FirstPage::class.java)
                startActivity(i)
                finish()
            }
        }
        t.schedule(t1, 3000)
    }
}