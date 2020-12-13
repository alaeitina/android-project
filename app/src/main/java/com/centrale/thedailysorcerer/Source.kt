package com.centrale.thedailysorcerer

import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Source (var id: String?, var name: String?):Parcelable {

    //private var id: String?
    //private var name: String?

    init {
        this.id = id
        this.name = name
    }

    fun getIcon(): Int = when (this.id) {
        "google-news-fr" -> R.drawable.google_news
        "le-monde" -> R.drawable.le_monde
        "lequipe" -> R.drawable.l_equipe
        "les-echos" -> R.drawable.les_echos
        "liberation" -> R.drawable.liberation
        else -> {
            R.drawable.ic_menu
        }
    }

    /*fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }*/

}