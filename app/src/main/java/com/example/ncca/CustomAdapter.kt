package com.example.ncca

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView


class CustomAdapter(

    var ctx:Context,
    var a1:MutableList<String>,
    var a2:MutableList<String>) : ArrayAdapter<Any?>(ctx,R.layout.lv,a1 as List<Any?>){
    var lf:LayoutInflater

    init {
        lf=LayoutInflater.from(ctx)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        view = lf.inflate(R.layout.lv, parent, false)
        val tv1 = view!!.findViewById<View>(R.id.item_name) as TextView
        val tv2 = view!!.findViewById<View>(R.id.area_name) as TextView

        tv1.text = a1[position]
        tv2.text = a2[position]

    return view
    }



    }

