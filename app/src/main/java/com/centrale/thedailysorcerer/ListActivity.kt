package com.centrale.thedailysorcerer

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import eu.corellis.centrale.activitylifecycle.CustomAdapter
import layout.Article
import org.json.JSONArray
import org.json.JSONObject

class ListActivity : AppCompatActivity() {

    val TAG:String = "ListActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)



        val i = intent
        val listSources:ArrayList<Source> = i.getParcelableArrayListExtra<Source>("list") as ArrayList<Source>
        val sourcesForSpinner: ArrayList<CharSequence> = arrayListOf<CharSequence>()

        for (j in 0 until listSources.size) {
            listSources[j].name?.let { sourcesForSpinner.add(it) }
        }

        val spinner = findViewById<View>(R.id.spinner_sources) as Spinner

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, sourcesForSpinner)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = aa

        if(savedInstanceState == null) {
            listFragment = ListArticlesFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.list_fragment, ListArticlesFragment.newInstance())
                .commit()
        }

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {


                thisSource = listSources.get(position)
                thisPage = 1
                listFragment?.clearArticles()
                getContent()

                Log.d(TAG, "After get Content : "+listFragment!!.dataset.toString())



            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }




    }


}