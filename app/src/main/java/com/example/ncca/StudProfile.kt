package com.example.ncca

import android.annotation.SuppressLint
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class StudProfile : AppCompatActivity() {
    var tx1 : TextView? = null
    var tx2 : TextView? = null
    var tx3 : TextView? = null
    var tx4 : TextView? = null
    var tx5 : TextView? = null
    var tx6 : TextView? = null
    var tx7 : TextView? = null
    var tx8 : TextView? = null
    var tx9 : TextView? = null
    var g: DBHelper? = null

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stud_profile)

        tx1 = findViewById<View>(R.id.tx1) as TextView
        tx2 = findViewById<View>(R.id.tx2) as TextView
        tx3 = findViewById<View>(R.id.tx3) as TextView
        tx4 = findViewById<View>(R.id.tx4) as TextView
        tx5 = findViewById<View>(R.id.tx5) as TextView
        tx6 = findViewById<View>(R.id.tx6) as TextView
        tx7 = findViewById<View>(R.id.tx7) as TextView
        tx8 = findViewById<View>(R.id.tx8) as TextView
        tx9 = findViewById<View>(R.id.tx9) as TextView

        g= DBHelper(this)
        val us = intent.getStringExtra("user")

        var c: Cursor = g!!.get_User(us.toString())
        if (c.moveToFirst()) {
            do {
                tx1!!.setText(c.getString(c.getColumnIndex("Name")))
                tx2!!.setText(c.getString(c.getColumnIndex("SID")))
                tx3!!.setText(c.getString(c.getColumnIndex("Eno")))
                tx4!!.setText(c.getString(c.getColumnIndex("Cno")))
                tx5!!.setText(c.getString(c.getColumnIndex("Addr")))
                tx6!!.setText(c.getString(c.getColumnIndex("eid")))
                tx7!!.setText(c.getString(c.getColumnIndex("DOB")))
                tx8!!.setText(c.getString(c.getColumnIndex("year")))
                tx9!!.setText(c.getString(c.getColumnIndex("sem")))
            } while (c.moveToNext())
        }
        c.close()
    }
}

