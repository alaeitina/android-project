package com.centrale.thedailysorcerer

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(v:View) : RecyclerView.ViewHolder(v) {

    val TAG = "View Holder"

    val txtTitle: TextView
    val txtAuthor: TextView
    val txtDate: TextView
    //val imgArticle: ImageView

    init {
        v.setOnClickListener { Log.d(TAG, "Element $adapterPosition clicked.") }
        txtTitle = v.findViewById(R.id.txtTitle)
        txtAuthor = v.findViewById(R.id.txtAuthor)
        txtDate = v.findViewById(R.id.txtDate)
        //imgArticle = v.findViewById(R.id.imgArticle)
    }

}