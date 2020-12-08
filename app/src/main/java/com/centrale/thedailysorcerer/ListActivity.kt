package com.centrale.thedailysorcerer

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android. widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class ListActivity : AppCompatActivity() {

    val TAG:String = "ListActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val i = intent
        val listSources:ArrayList<Source> = i.getParcelableArrayListExtra<Source?>("list") as ArrayList<Source>

        val sourcesForSpinner: ArrayList<CharSequence> = arrayListOf<CharSequence>()

        for (i in 0 until listSources.size) {
            listSources[i].name?.let { sourcesForSpinner.add(it) }
        }

        val spinner = findViewById<View>(R.id.spinner_sources) as Spinner

        var aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, sourcesForSpinner)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = aa
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                /*if (nsf != null) {
                    supportFragmentManager.beginTransaction().remove(nsf).commit()
                    nsf = null
                }
                actualSource = sl.get(position)
                actualPage = 1
                loadedPage = 0
                frag.clearArticles()
                fetchNews()*/
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


    }


}