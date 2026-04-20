package com.example.ncca

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class notice : AppCompatActivity() {
    var fileList = ArrayList<String>()
    lateinit var adp: ArrayAdapter<String>
    var lv : ListView? = null
    var ins : Button? = null
    var g:DBHelper?=null
    var PICK_FILE_REQUEST_CODE = 1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        lv = findViewById<View>(R.id.lv3) as ListView
        ins = findViewById<View>(R.id.ins3) as Button
        adp = ArrayAdapter(this,android.R.layout.simple_list_item_1,fileList)
        lv!!.adapter = adp
        g = DBHelper(this)


        ins!!.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/*"
            startActivityForResult(intent, PICK_FILE_REQUEST_CODE)
        }

        val existingFiles = g?.getFileNames()
        if (existingFiles != null) {
            fileList.addAll(existingFiles)
            adp.notifyDataSetChanged()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val selectedFileUri = data?.data
            if (selectedFileUri != null) {
                val pdfFile = File(selectedFileUri.path)
                g!!.savePDFFile(this, pdfFile, 4)
                fileList.add(pdfFile.name)
                adp.notifyDataSetChanged()
            }
        }
    }

}