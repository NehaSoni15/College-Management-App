package com.example.ncca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button

class FirstPage : AppCompatActivity()
{ var b1: Button? = null
    var b2: Button? = null
    var b3: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_first_page)
        b1 = findViewById<View>(R.id.b1) as Button
        b2 = findViewById<View>(R.id.b2) as Button
        b3 = findViewById<View>(R.id.b3) as Button
        b1!!.setOnClickListener {
            val i = Intent(this@FirstPage, AdminLogin::class.java)
            startActivity(i)
        }
        b2!!.setOnClickListener {
            val i = Intent(this@FirstPage, FacLogin::class.java)
            startActivity(i)
        }
        b3!!.setOnClickListener {
            val i = Intent(this@FirstPage, StuLogin::class.java)
            startActivity(i)
        }
    }
}


