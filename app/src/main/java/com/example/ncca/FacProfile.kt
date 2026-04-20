package com.example.ncca

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import de.hdodenhof.circleimageview.CircleImageView

class FacProfile : AppCompatActivity() {
    var cv: CircleImageView? = null
    var ec: EditText? = null
    var txt1: TextView? = null
    var txt2: TextView? = null
    var txt3: TextView? = null
    var txt4: TextView? = null
    var txt5: TextView? = null
    var submit: Button? = null
    var PICK_FILE_REQUEST_CODE = 1
    var g: DBHelper? = null

    @SuppressLint("Range", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fac_profile)

        cv = findViewById<View>(R.id.cv) as CircleImageView
        ec = findViewById<View>(R.id.ec) as EditText
        txt1 = findViewById<View>(R.id.txt1) as TextView
        txt2 = findViewById<View>(R.id.txt2) as TextView
        txt3 = findViewById<View>(R.id.txt3) as TextView
        txt4 = findViewById<View>(R.id.txt4) as TextView
        txt5 = findViewById<View>(R.id.txt5) as TextView
        submit = findViewById<View>(R.id.sub) as Button


        g = DBHelper(this)

        val us = intent.getStringExtra("user")

        var c: Cursor = g!!.getUser(us.toString())
        if (c.moveToFirst()) {
            do {
                txt1!!.setText(c.getString(c.getColumnIndex("Name")))
                txt2!!.setText(c.getString(c.getColumnIndex("Addr")))
                txt3!!.setText(c.getString(c.getColumnIndex("Cno")))
                txt4!!.setText(c.getString(c.getColumnIndex("Age")))
                txt5!!.setText(c.getString(c.getColumnIndex("FID")))
            } while (c.moveToNext())
        }
        c.close()

        val sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val savedImageUri = sharedPrefs.getString("profile_image_uri", null)


        cv!!.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_FILE_REQUEST_CODE)
        }
    }


    private fun convertImageToByteArray(imageUri: Uri): ByteArray {
        val inputStream = contentResolver.openInputStream(imageUri)
        return inputStream?.readBytes() ?: ByteArray(0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val selectedFileUri = data?.data
            if (selectedFileUri != null) {
                // Set the selected image as the profile picture
                val imageByteArray = convertImageToByteArray(selectedFileUri)

                cv!!.setImageURI(selectedFileUri)


                // Save the selected image URI in SharedPreferences
                val sharedPrefsEditor = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).edit()
                sharedPrefsEditor.putString("profile_image_uri", selectedFileUri.toString())
                sharedPrefsEditor.apply()

                // Now, insert the image data along with other information into the database
                val us = intent.getStringExtra("user")
                val a = g!!.insertFacProf(ec!!.text.toString(), us.toString(), imageByteArray)

                if (a) {
                    Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Profile Not Updated", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}










