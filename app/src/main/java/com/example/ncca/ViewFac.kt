package com.example.ncca

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ViewFac<ItemAdo> : AppCompatActivity() {
   var lv1: ListView? = null
    val g= DBHelper(this)

    @SuppressLint("Range", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_fac)

        val lv1 = findViewById<ListView>(R.id.lv)
        val cursor = g.getAllItems()
        val items1 = mutableListOf<String>()
        val items2 = mutableListOf<String>()
        if (cursor.moveToFirst()) {
             do {

                 val name = cursor.getString(cursor.getColumnIndex("Name"))
                 val age = cursor.getString(cursor.getColumnIndex("Age"))
                 items1.add(name)
                 items2.add(age)
             } while (cursor.moveToNext())
         }
         cursor.close()

        val adapter :CustomAdapter = CustomAdapter(this, items1,items2)
                lv1.setAdapter(adapter)
            }
        }


















