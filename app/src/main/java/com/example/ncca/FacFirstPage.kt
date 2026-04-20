package com.example.ncca

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FacFirstPage : AppCompatActivity() {
    var stu: Button? = null
    var doc: Button? = null
    var pfp: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fac_first_page)

        stu=findViewById<View>(R.id.stu) as Button
        doc=findViewById<View>(R.id.doc) as Button
        pfp=findViewById<View>(R.id.pfp) as Button

        val uname = intent.getStringExtra("username")

        stu!!.setOnClickListener {

            val i = Intent(this@FacFirstPage, AdminStudPage::class.java)
            startActivity(i)
        }

        doc!!.setOnClickListener {

            val i = Intent(this@FacFirstPage, Document::class.java)
            startActivity(i)
        }

        pfp!!.setOnClickListener {
            val i = Intent(this@FacFirstPage, FacProfile::class.java)
            i.putExtra("user",uname.toString())
            startActivity(i)
        }
    }
}
