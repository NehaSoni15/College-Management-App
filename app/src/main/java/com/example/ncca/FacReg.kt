package com.example.ncca

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FacReg : AppCompatActivity() {
    var reg: Button? = null
    var eName: EditText? = null
    var eAge: EditText? = null
    var Cno: EditText? = null
    var Address: EditText? = null
    var UID: EditText? = null
    var Pass: EditText? = null
    var g: DBHelper? = null

    private val tw: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            val s1 = eName!!.text.toString()
            val s2 = eAge!!.text.toString()
            val s3 = Cno!!.text.toString()
            val s4 = Address!!.text.toString()
            val s5 = UID!!.text.toString()
            val s6 = Pass!!.text.toString()


            reg!!.setBackgroundColor(Color.parseColor("#2196F3"))
            reg!!.isEnabled =
                !s1.isEmpty() && !s2.isEmpty() && !s2.isEmpty() && !s3.isEmpty() && !s4.isEmpty() && !s5.isEmpty()
                        && !s6.isEmpty()

        }

        override fun afterTextChanged(editable: Editable) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fac_reg)

        reg = findViewById<View>(R.id.reg) as Button
        eName = findViewById<View>(R.id.eName) as EditText
        eAge = findViewById<View>(R.id.eAge) as EditText
        Cno = findViewById<View>(R.id.Cno) as EditText
        Address = findViewById<View>(R.id.Address) as EditText
        UID = findViewById<View>(R.id.UID) as EditText
        Pass = findViewById<View>(R.id.Pass) as EditText

        g = DBHelper(this)

        eName!!.addTextChangedListener(tw)
        eAge!!.addTextChangedListener(tw)
        Cno!!.addTextChangedListener(tw)
        Address!!.addTextChangedListener(tw)
        UID!!.addTextChangedListener(tw)
        Pass!!.addTextChangedListener(tw)

        reg!!.setOnClickListener {
            val s1: String = eName!!.getText().toString()
            val s2: String = eAge!!.getText().toString()
            val f1 = s2.toInt()
            val s3: String = Cno!!.getText().toString()
            val s4: String = Address!!.getText().toString()
            val s5: String = UID!!.getText().toString()
            val s6: String = Pass!!.getText().toString()

            val n: Boolean = g!!.CheckfacUsername(UID!!.text.toString())
            if (n == false) {
                UID!!.setError("Username already exists!")
            } else {


                if (s1 == "" || s2 == "" || s3 == "" || s4 == "" || s5 == "" || s6 == "") {
                    Toast.makeText(
                        this@FacReg, "Enter All Fields Values", Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val i = g!!.insert_fac(s1, f1, s3, s4, s5, s6)
                    if (i == true) {
                        Toast.makeText(this@FacReg, "Registered Successfully", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this@FacReg, "Registration Unsuccessful", Toast.LENGTH_SHORT)
                            .show()
                    }
                }


                eName!!.setText("")
                eAge!!.setText("")
                Cno!!.setText("")
                Address!!.setText("")
                UID!!.setText("")
                Pass!!.setText("")

                val i = Intent(this@FacReg, FacLogin::class.java)
                startActivity(i)

            }
        }
    }
}


