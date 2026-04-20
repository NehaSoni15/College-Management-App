package com.example.ncca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class AdminFirstPage : AppCompatActivity() {
    var fac: Button? = null
    var stu:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_first_page)

        fac = findViewById<View>(R.id.fac) as Button
        stu= findViewById<View>(R.id.stu) as Button

        fac!!.setOnClickListener {
            val i = Intent(this@AdminFirstPage,AdminFacultyPage::class.java)
            startActivity(i)

        }

        stu!!.setOnClickListener {
            val i = Intent(this@AdminFirstPage,AdminStudPage::class.java)
            startActivity(i)

        }
    }
}