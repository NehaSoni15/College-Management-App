package com.example.ncca

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class StuReg : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    var reg1: Button? = null
    var E1: EditText? = null
    var E2: EditText? = null
    var E3: EditText? = null
    var E4: EditText? = null
    var E5: EditText? = null
    var E6: EditText? = null
    var SID:EditText? = null

    var ed: EditText? = null
    var rg1: RadioGroup? = null
    var rg2: RadioGroup? = null

    var rb1: RadioButton? = null
    var rb2: RadioButton? = null
    var rb3: RadioButton? = null
    var r1: RadioButton? = null
    var r2: RadioButton? = null
    var r3: RadioButton? = null
    var r4: RadioButton? = null
    var r5: RadioButton? = null
    var r6: RadioButton? = null

    var g: DBHelper? = null

    private val tw: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            val s1 = E1!!.text.toString()
            val s2 = E2!!.text.toString()
            val s3 = E3!!.text.toString()
            val s4 = E4!!.text.toString()
            val s5 = E5!!.text.toString()
            val s6 = E6!!.text.toString()
            val s7 = SID!!.text.toString()
            val s8 = ed!!.text.toString()

            reg1!!.setBackgroundColor(Color.parseColor("#2196F3"))
            reg1!!.isEnabled = !s1.isEmpty() && !s2.isEmpty() && !s2.isEmpty() && !s3.isEmpty() && !s4.isEmpty() && !s5.isEmpty()
                    && !s6.isEmpty() && !s7.isEmpty() && !s8.isEmpty()

        }

        override fun afterTextChanged(editable: Editable) {}
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stu_reg)

        reg1=findViewById<View>(R.id.reg1) as Button
        E1=findViewById<View>(R.id.E1) as EditText
        E2=findViewById<View>(R.id.E2) as EditText
        E3=findViewById<View>(R.id.E3) as EditText
        E4=findViewById<View>(R.id.E4) as EditText
        E5=findViewById<View>(R.id.E5) as EditText
        E6=findViewById<View>(R.id.E6) as EditText
        SID=findViewById<View>(R.id.SID) as EditText
        ed=findViewById<View>(R.id.ed) as EditText
        rg1=findViewById<View>(R.id.rg1) as RadioGroup
        rg2=findViewById<View>(R.id.rg2) as RadioGroup

        rb1=findViewById<View>(R.id.rb1) as RadioButton
        rb2=findViewById<View>(R.id.rb2) as RadioButton
        rb3=findViewById<View>(R.id.rb3) as RadioButton
        r1=findViewById<View>(R.id.r1) as RadioButton
        r2=findViewById<View>(R.id.r2) as RadioButton
        r3=findViewById<View>(R.id.r3) as RadioButton
        r4=findViewById<View>(R.id.r4) as RadioButton
        r5=findViewById<View>(R.id.r5) as RadioButton
        r6=findViewById<View>(R.id.r6) as RadioButton

        g = DBHelper(this)

        E1!!.addTextChangedListener(tw)
        E2!!.addTextChangedListener(tw)
        E3!!.addTextChangedListener(tw)
        E4!!.addTextChangedListener(tw)
        E5!!.addTextChangedListener(tw)
        E6!!.addTextChangedListener(tw)
        SID!!.addTextChangedListener(tw)

        ed!!.setOnClickListener {

            val c = Calendar.getInstance()
            val year : Int = c.get(Calendar.YEAR)
            val month : Int= c.get(Calendar.MONTH)
            val day : Int = c.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, day ->

                val m = month+1
                ed!!.setText("" + day + "/ " + m + "/ " + year)
            }, year, month, day)

            dpd.show()

        }


        reg1!!.setOnClickListener {

            val s1: String = E1!!.text.toString()
            val s2: String = E2!!.text.toString()
            val s3: String = E3!!.text.toString()
            val s4: String = E4!!.text.toString()
            val s5: String = E5!!.text.toString()
            val s6: String = ed!!.text.toString()
            var s7: String? = null
            val s10: String = SID!!.getText().toString()
            val s11: String = E6!!.getText().toString()

            if (rb1!!.isChecked) {
                s7= "1st Year"
            }
            else if (rb2!!.isChecked){
                s7= "2nd Year"
            }
            else if (rb3!!.isChecked){
                s7= "3rd Year"
            }

            var s8: String? = null

            if (r1!!.isChecked) {
                s8= "1"
            }
            else if (r2!!.isChecked){
                s8= "2"
            }
            else if (r3!!.isChecked){
                s8= "3"
            }
            else if (r4!!.isChecked){
                s8= "4"
            }
            else if (r5!!.isChecked){
                s8= "5"
            }
            else if (r6!!.isChecked){
                s8= "6"
            }
            val f1 = s8!!.toInt()




            val n: Boolean = g!!.CheckstudUsername(SID!!.text.toString())
            if (n == false) {
                SID!!.setError("Username already exists!")
            } else {

                if (s1 == "" || s2 == "" || s3 == "" || s4 == "" || s5 == "" || s6 == "" || s10 == "" || s11 == "") {
                    Toast.makeText(this@StuReg, "Enter All Fields Values", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val i = g!!.insert_stu(s1, s2, s3, s4, s5, s6, s7, f1, s10,s11)
                    if (i == true) {
                        Toast.makeText(this@StuReg, "Registered Successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@StuReg, "Registration Unsuccessful", Toast.LENGTH_SHORT).show()
                    }

                }
            }
            E1!!.setText("")
            E2!!.setText("")
            E3!!.setText("")
            E4!!.setText("")
            E5!!.setText("")
            E6!!.setText("")

            val i = Intent(this@StuReg, StuLogin::class.java)
            startActivity(i)
        }


    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        TODO("Not yet implemented")
    }

}
