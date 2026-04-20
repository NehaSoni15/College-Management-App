package com.example.ncca

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class DBHelper(context: Context?) : SQLiteOpenHelper(context, dbname, null, 1) {

    val a = "Create table faculty(Name text,Age number,Cno text, Addr text, FID varchar primary key, pass varchar)"
    val b = "Create table student(Name text,Eno text,Cno text, Addr text, eid varchar, DOB varchar, year varchar, sem number,  SID varchar primary key, pass varchar )"
    val c = "Create table DocMaster(DID number primary key , Dtype text)"
    val d = "Create table Document(Name text, Date varchar,FPath text, DID number, foreign key(DID) references DocMaster(DID))"
    val e = "Create table facprof(profilepic blob, about text, FID varchar, foreign key(FID) references faculty(FID))"


    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(a)
        sqLiteDatabase.execSQL(b)
        sqLiteDatabase.execSQL(c)
        sqLiteDatabase.execSQL(d)
        sqLiteDatabase.execSQL(e)
    }



    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        sqLiteDatabase.execSQL("drop table if exists faculty")
        sqLiteDatabase.execSQL("drop table if exists student")
        sqLiteDatabase.execSQL("drop table if exists DocMaster")
        sqLiteDatabase.execSQL("drop table if exists Document")
        sqLiteDatabase.execSQL("drop table if exists facprof")
        onCreate(sqLiteDatabase)
    }

    fun insert_fac(r: String?, a: Int?, c: String?, ad: String?, u: String?, p: String?): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("Name", r)
        cv.put("Age", a)
        cv.put("Cno", c)
        cv.put("Addr", ad)
        cv.put("FID", u)
        cv.put("pass", p)
        val result = db.insert("faculty", null, cv)
        db.close()
        return if (result == -1L) {
            false
        }
        else {
            true
        }
    }


    fun insert_stu(n: String?, en: String?, c: String?, ad: String?, e: String?, d: String?, y: String?, s: Int?, u:String?, p:String?): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("Name", n)
        cv.put("Eno", en)
        cv.put("Cno", c)
        cv.put("Addr", ad)
        cv.put("eid", e)
        cv.put("DOB", d)
        cv.put("year", y)
        cv.put("sem", s)
        cv.put("SID", u)
        cv.put("pass", p)

        val result = db.insert("student", null, cv)
        db.close()
        return if (result == -1L) {
            false
        }
        else {
            true
        }
    }




    fun insert_doc(){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("DID",1)
        cv.put("Dtype","Assignment")
        db.insert("DocMaster", null ,cv)
        cv.put("DID",2)
        cv.put("Dtype","Notes")
        db.insert("DocMaster", null ,cv)
        cv.put("DID",3)
        cv.put("Dtype","TimeTable")
        db.insert("DocMaster", null ,cv)
        cv.put("DID",4)
        cv.put("Dtype","Notice")
        db.insert("DocMaster", null ,cv)
        db.close()

    }


    fun insertFacProf(about: String, fid: String, profilePicData: ByteArray): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put("about", about)
        cv.put("FID", fid)
        cv.put("profilepic", profilePicData) // Insert the image data

        val result = db.insert("facprof", null, cv)
        db.close()
        return result != -1L
    }


    fun CheckFacLogin(s1:String,s2:String) : Boolean {
        val db=writableDatabase
        val  cursor=db.rawQuery("select * from faculty where FID=? and pass=?", arrayOf(s1,s2))
        return if (cursor.getCount() > 0)
        {
            true
        }
        else
        {
            false
        }
    }



    fun CheckStudLogin(s1:String,s2:String) : Boolean {
        val db=writableDatabase
        val  cursor=db.rawQuery("select * from student where SID=? and pass=?", arrayOf(s1,s2))
        return if (cursor.getCount() > 0)
        {
            true
        }
        else
        {
            false
        }
    }

    fun getinfo(year:String): Int {
        val db = this.writableDatabase
        val c: Cursor= db.rawQuery("select count(*) from student where year=?", arrayOf(year))
        c.moveToFirst()
        val i : Int = c.getInt(0)
        c.close()
        return i
    }



   /* fun getfacinfo(): Cursor? {
        val db = this.writableDatabase
        return db.rawQuery("select * from faculty", null)
    }

    fun getstudinfo(): Cursor? {
        val db = this.writableDatabase
        return db.rawQuery("select * from student", null)
    }

    */



    fun CheckfacUsername(FID: String): Boolean {
        val sqLiteDatabase = this.readableDatabase
        val cursor = sqLiteDatabase.rawQuery("SELECT * FROM faculty WHERE FID=?",arrayOf(FID))
        if (cursor.count > 0) {
            return false
        } else {
            return  true
        }
    }


    fun CheckstudUsername(SID: String): Boolean {
        val sqLiteDatabase = this.readableDatabase
        val cursor = sqLiteDatabase.rawQuery("SELECT * FROM student WHERE SID=?", arrayOf(SID))
        if (cursor.count > 0) {
            return false
        } else {
            return  true
        }
    }


    fun getAllItems(): Cursor {
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM faculty", null)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun savePDFFile(context: Context, pdfFile: File, d :Int): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("Name", pdfFile.name)

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val c = sdf.format(Date())

        values.put("Date",c)
        values.put("FPath", pdfFile.absolutePath)
        values.put("DID",d)
        val id = db.insert("Document", null, values)
        db.close()
        return id
    }




    fun getFileNames(): List<String> {
        val fileList = mutableListOf<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Document", null)
        while (cursor.moveToNext()) {
            fileList.add(cursor.getString(0))
            fileList.add(cursor.getString(1))
        }
        cursor.close()
        return fileList
    }

    @SuppressLint("Range")
    fun remfac(name: String){
        val db = writableDatabase
        val item1 = mutableListOf<String>()
        val c: Cursor = db.rawQuery("SELECT FID FROM faculty WHERE Name=?", arrayOf(name))
        if (c.moveToFirst()) {
            do {
                val a = c.getString(c.getColumnIndex("FID"))
                item1.add(a)
            } while (c.moveToNext())
        }
        val itm = item1[0]
        db.execSQL("delete from faculty where FID=?", arrayOf(itm))
        c.close()
    }

    fun getUser(FID: String): Cursor {
        val sqLiteDatabase = this.readableDatabase
        val res: Cursor = sqLiteDatabase.rawQuery("SELECT * FROM faculty WHERE FID=?", arrayOf(FID))
        return res
    }

    fun get_User(SID: String): Cursor {
        val sqLiteDatabase = this.readableDatabase
        val res: Cursor = sqLiteDatabase.rawQuery("SELECT * FROM student WHERE SID=?", arrayOf(SID))
        return res
    }

    fun getDoc ():Cursor {
        val db = readableDatabase
        return db.rawQuery("select * from Document", null)
    }
    fun getStu(yr: String): Cursor {
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM student where year=?", arrayOf(yr))
    }

    fun viewfac(name: String): Cursor{
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM faculty WHERE Name=?", arrayOf(name))
    }
    fun viewstu(name: String): Cursor{
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM student WHERE Name=?", arrayOf(name))
    }

    companion object {
        private const val dbname = "NCCA.db"
    }
}

