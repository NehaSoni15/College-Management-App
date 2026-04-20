package com.example.ncca

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AdminStudPage : AppCompatActivity() {

    var fy: Button? = null
    var sy: Button? = null
    var ty: Button? = null
    var tv1: TextView? = null
    var tv2: TextView? = null
    var tv3: TextView? = null
    var g: DBHelper?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_admin_stud_page)

        fy = findViewById<View>(R.id.fy) as Button
        sy= findViewById<View>(R.id.sy) as Button
        ty = findViewById<View>(R.id.ty) as Button
        tv1 = findViewById<View>(R.id.fy1) as TextView
        tv2 = findViewById<View>(R.id.sy1) as TextView
        tv3 = findViewById<View>(R.id.ty1) as TextView

        g = DBHelper(this)

        var i1 : Int = g!!.getinfo("1st Year")
        tv1!!.setText(i1.toString())

        var i2 : Int = g!!.getinfo("2nd Year")
        tv2!!.setText(i2.toString())

        var i3 : Int = g!!.getinfo("3rd Year")
        tv3!!.setText(i3.toString())

        fy!!.setOnClickListener {
            val i1 = Intent(this@AdminStudPage, StudFy::class.java)
            i1.putExtra("year", "1st Year")
            startActivity(i1)
        }

            sy!!.setOnClickListener {
                val i2 = Intent(this@AdminStudPage, StudFy::class.java)
                i2.putExtra("year", "2nd Year")
                startActivity(i2)
            }

                ty!!.setOnClickListener {
                    val i3 = Intent(this@AdminStudPage, StudFy::class.java)
                    i3.putExtra("year", "3rd Year")
                    startActivity(i3)
                }

                }
    }

