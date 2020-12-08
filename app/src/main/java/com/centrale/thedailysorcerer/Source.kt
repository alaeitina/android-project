package com.centrale.thedailysorcerer

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