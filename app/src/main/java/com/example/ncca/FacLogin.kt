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

class FacLogin : AppCompatActivity() {
    var e3: EditText? = null
    var e4: EditText? = null
    var fb: Button? = null
    var tv1: TextView? = null
    var g: DBHelper? = null
    private val tw: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            val s1 = e3!!.text.toString()
            val s2 = e4!!.text.toString()
            fb!!.setBackgroundColor(Color.parseColor("#2196F3"))
            fb!!.isEnabled = !s1.isEmpty() && !s2.isEmpty()

        }

        override fun afterTextChanged(editable: Editable) {}
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_fac_login)

        e3 = findViewById<View>(R.id.e3) as EditText
        e4 = findViewById<View>(R.id.e4) as EditText
        fb = findViewById<View>(R.id.fb) as Button

        g = DBHelper(this)

        e3!!.addTextChangedListener(tw)
        e4!!.addTextChangedListener(tw)

        var s1:String = e3!!.text.toString()
        var s2:String = e4!!.text.toString()


        tv1 = findViewById<View>(R.id.tv1) as TextView
        tv1!!.setOnClickListener {

            val i = Intent(this@FacLogin, FacReg::class.java)
            startActivity(i)
        }


        fb!!.setOnClickListener {
            val i: Boolean = g!!.CheckFacLogin(e3!!.text.toString(), e4!!.text.toString())
            if (i == true) {
                val intent= Intent(this@FacLogin, FacFirstPage::class.java)
                intent.putExtra("username", e3!!.text.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this@FacLogin, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }


    }
}