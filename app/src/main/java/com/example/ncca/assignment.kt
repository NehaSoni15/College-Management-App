package com.example.ncca

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class assignment : AppCompatActivity() {
    var fileList = ArrayList<String>()
    lateinit var adp: ArrayAdapter<String>
    var lv : ListView? = null
    var ins : Button? = null
    var g:DBHelper?=null
    var PICK_FILE_REQUEST_CODE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment)

        lv = findViewById(R.id.lv)
        ins = findViewById(R.id.ins)
        adp = ArrayAdapter(this, android.R.layout.simple_list_item_1, fileList)
        lv!!.adapter = adp
        g = DBHelper(this)

        ins!!.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/*" // You can specify a specific file type here if needed
            startActivityForResult(intent, PICK_FILE_REQUEST_CODE)
        }

        // Load existing files from the database and add them to the list
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
                g!!.savePDFFile(this, pdfFile, 1)
                fileList.add(pdfFile.name)
                adp.notifyDataSetChanged()
            }


        }
    }
}

