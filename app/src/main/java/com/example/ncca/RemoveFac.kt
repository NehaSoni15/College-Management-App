package com.example.ncca

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class RemoveFac : AppCompatActivity() {
    var lv1: ListView? = null
    var g:DBHelper? = null

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_fac)

        lv1=findViewById<View>(R.id.lv2) as ListView
        g =DBHelper(this)

        val c1 = g!!.getAllItems()

        val item1 = mutableListOf<String>()
        val item2 =  mutableListOf<String>()
        if (c1.moveToFirst()) {
            do {
                val name = c1.getString(c1.getColumnIndex("Name"))
                val age = c1.getString(c1.getColumnIndex("Age"))
                item1.add(name)
                item2.add(age)
            } while (c1.moveToNext())
        }
        c1.close()


        val ad1 = CustomAdapter(this, item1, item2)
        lv1!!.setAdapter(ad1)

        lv1!!.setOnItemClickListener { adapterView, view, i, l ->
            val ad = AlertDialog.Builder(this@RemoveFac)
            ad.setCancelable(true)
            ad.setTitle("Remove Faculty")
            ad.setIcon(R.drawable.baseline_remove_circle_24)
            ad.setMessage("Are you sure you want to remove faculty?")
            ad.setNegativeButton("No") { dialog, which ->
                Toast.makeText(this@RemoveFac, "Faculty not removed", Toast.LENGTH_SHORT).show()
            }
            ad.setPositiveButton("Yes") { dialog, which ->

                val selectedItem :String = adapterView.getItemAtPosition(i).toString();
                g!!.remfac(selectedItem)
                Toast.makeText(this@RemoveFac, "Faculty Removed", Toast.LENGTH_SHORT).show()
            }
            ad.show()
        }


    }
}
