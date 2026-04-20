package com.example.ncca

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
//import assignment

class Document : AppCompatActivity() {
    var iv1: ImageView? =null
    var iv2:ImageView? =null
    var iv3:ImageView? =null
    var iv4:ImageView? =null
    var g:DBHelper?=null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document)

                iv1 = findViewById<View>(R.id.iv1) as ImageView
                iv2 = findViewById<View>(R.id.iv2) as ImageView
                iv3 = findViewById<View>(R.id.iv3) as ImageView
                iv4 = findViewById<View>(R.id.iv4) as ImageView
                g= DBHelper(this)

                g!!.insert_doc()

                iv1!!.setOnClickListener {

                    val i = Intent(this@Document,assignment::class.java)
                    startActivity(i)
                }

                iv2!!.setOnClickListener {

                    val i =Intent(this@Document,notes::class.java)
                    startActivity(i)
                }

                iv3!!.setOnClickListener {

                    val i =Intent(this@Document,timetable::class.java)
                    startActivity(i)
                }

                iv4!!.setOnClickListener {

                    val i =Intent(this@Document,notice::class.java)
                    startActivity(i)
                }
            }
        }


