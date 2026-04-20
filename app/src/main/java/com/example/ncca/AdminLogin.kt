package com.example.ncca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AdminLogin : AppCompatActivity() {
    var b4: Button? = null
    var e1: EditText? = null
    var e2: EditText? = null
    private val tw: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            val s1 = e1!!.text.toString()
            val s2 = e2!!.text.toString()
            b4!!.isEnabled = !s1.isEmpty() && !s2.isEmpty()
        }

        override fun afterTextChanged(editable: Editable) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_admin_login)
        b4 = findViewById<View>(R.id.b4) as Button
        e1 = findViewById<View>(R.id.e1) as EditText
        e2 = findViewById<View>(R.id.e2) as EditText
        e1!!.addTextChangedListener(tw)
        e2!!.addTextChangedListener(tw)
        b4!!.setOnClickListener {
            val s1 = e2!!.text.toString()
            if (s1 == "admin") {
                //Toast.makeText(this@AdmLogin, "Redirecting...", Toast.LENGTH_SHORT).show()
                val i = Intent(this@AdminLogin, AdminFirstPage::class.java)
                startActivity(i)
            } else {
                Toast.makeText(this@AdminLogin, "Incorrect Password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
