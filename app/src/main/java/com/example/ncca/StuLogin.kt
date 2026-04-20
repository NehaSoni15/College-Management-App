package com.example.ncca

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StuLogin : AppCompatActivity() {
    var e5: EditText? = null
    var e6: EditText? = null
    var sb: Button? = null
    var tv1: TextView? = null
    var g: DBHelper? = null

    private val tw: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            val s1 = e5!!.text.toString()
            val s2 = e6!!.text.toString()
            sb!!.setBackgroundColor(Color.parseColor("#2196F3"))

            sb!!.isEnabled = !s1.isEmpty() && !s2.isEmpty()

        }

        override fun afterTextChanged(editable: Editable) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_stu_login)
        e5 = findViewById<View>(R.id.e5) as EditText
        e6 = findViewById<View>(R.id.e6) as EditText
        sb = findViewById<View>(R.id.sb) as Button

        g = DBHelper(this)

        e5!!.addTextChangedListener(tw)
        e6!!.addTextChangedListener(tw)


        tv1 = findViewById<View>(R.id.tv1) as TextView
        tv1!!.setOnClickListener {

            val i = Intent(this@StuLogin, StuReg::class.java)
            startActivity(i)
        }

        sb!!.setOnClickListener {

            val i: Boolean = g!!.CheckStudLogin(e5!!.text.toString(), e6!!.text.toString())
            if (i == true) {
                val i1 = Intent(this@StuLogin, StudFirstPage::class.java)
                i1.putExtra("username", e5!!.text.toString())
                startActivity(i1)
            } else {
                Toast.makeText(this@StuLogin, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}