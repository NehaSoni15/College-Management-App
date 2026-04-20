package com.example.ncca

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AdminFacultyPage : AppCompatActivity() {

    var addfac: Button? = null
    var remfac: Button? = null
    var Vfac: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_faculty_page)

        addfac = findViewById<View>(R.id.addfac) as Button
        remfac = findViewById<View>(R.id.remfac) as Button
        Vfac = findViewById<View>(R.id.Vfac) as Button
        Vfac!!.setOnClickListener {
            val i = Intent(this@AdminFacultyPage,ViewFac::class.java)
            startActivity(i)

        }
        addfac!!.setOnClickListener {
            val intent=Intent(this@AdminFacultyPage,FacReg::class.java)
            startActivity(intent)
        }
        remfac!!.setOnClickListener {
            val i2=Intent(this@AdminFacultyPage,RemoveFac::class.java)
            startActivity(i2)
        }

    }

}