package com.example.ncca

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView

class StudFy : AppCompatActivity() {
    var lv : ListView? = null
    var g : DBHelper? = null

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stud_fy)

        lv=findViewById<View>(R.id.lst) as ListView
        g =DBHelper(this)

        val yr = intent.getStringExtra("year")

        val c1 = g!!.getStu(yr.toString())

        val item1 = mutableListOf<String>()
        val item2 =  mutableListOf<String>()

        if (c1.moveToFirst()) {
            do {
                val name = c1.getString(c1.getColumnIndex("Name"))
                val y = c1.getString(c1.getColumnIndex("year"))
                item1.add(name)
                item2.add(y)
            } while (c1.moveToNext())
        }
        c1.close()

        val ad1 = CustomAdapter(this, item1, item2)
        lv!!.setAdapter(ad1)

        lv!!.setOnItemClickListener { adapterView, view, i, l ->

            val selectedItem :String = adapterView.getItemAtPosition(i).toString();
            val c: Cursor = g!!.viewstu(selectedItem)
            val itm = mutableListOf<String>()
            if (c.moveToFirst()) {
                do {
                    val sid = c.getString(c.getColumnIndex("SID"))
                    itm.add(sid)
                } while (c1.moveToNext())
            }
            val a =itm[0]
            val i1 = Intent(this@StudFy,StudProfile::class.java)
            i1.putExtra("user",a)
            startActivity(i1)
            c1.close()
        }

    }
}
