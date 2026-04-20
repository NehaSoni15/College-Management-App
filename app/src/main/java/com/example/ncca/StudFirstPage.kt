package com.example.ncca

import StudDoc
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StudFirstPage : AppCompatActivity() {

    var prf: Button? = null
    var doc: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stud_first_page)

        prf=findViewById<View>(R.id.pro) as Button
        doc=findViewById<View>(R.id.doc1) as Button

        val uname = intent.getStringExtra("username")


        prf!!.setOnClickListener {

            val i = Intent(this@StudFirstPage, StudProfile::class.java)
            i.putExtra("user",uname.toString())
            startActivity(i)
        }

        doc!!.setOnClickListener {

            val i2 = Intent(this@StudFirstPage,StudDoc::class.java)
            startActivity(i2)
        }


    }
}