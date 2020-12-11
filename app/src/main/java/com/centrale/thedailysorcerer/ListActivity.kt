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
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import eu.corellis.centrale.activitylifecycle.CustomAdapter
import layout.Article
import org.json.JSONArray
import org.json.JSONObject

class ListActivity : AppCompatActivity(), CustomAdapter.OnArticleSelectedListener {

    val TAG:String = "ListActivity"

    val uriContentSources:String = "https://newsapi.org/v2/everything?apiKey=cfe66a38eadc47448a0eb945629ba205&language=fr&sources="

    var thisSource: Source? = null
    var thisPage:Int = 1

    var listFragment: ListArticlesFragment? = null

    lateinit var queue: RequestQueue


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)



        val i = intent
        val listSources:ArrayList<Source> = i.getParcelableArrayListExtra<Source>("list") as ArrayList<Source>
        val sourcesForSpinner: ArrayList<CharSequence> = arrayListOf<CharSequence>()

        for (j in 0 until listSources.size) {
            listSources[j].name?.let { sourcesForSpinner.add(it) }
        }

        queue = Volley.newRequestQueue(this)

        val spinner = findViewById<View>(R.id.spinner_sources) as Spinner

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, sourcesForSpinner)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = aa

        if(savedInstanceState == null) {
            listFragment = ListArticlesFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment, ListArticlesFragment.newInstance())
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
                //Log.d(TAG, listFragment?.viewAdapter.toString())

                //listFragment?.viewAdapter!!.notifyDataSetChanged()




            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d(TAG, "No selected : $thisSource")
            }
        }




    }

    fun getContent() {
        // Request a string response from the provided URL.
        val stringReq : StringRequest = object : StringRequest(

            Method.GET, uriContentSources+ thisSource!!.id+"&page="+thisPage,
            Response.Listener<String> { response ->

                var strResp = response.toString()
                //Log.d(TAG, strResp)
                val result = JSONObject(strResp)
                val listArticles: JSONArray = result.getJSONArray("articles")
                Log.d(TAG, listArticles.toString())
                val theseArticles: ArrayList<Article?> = arrayListOf<Article?>()

                for (i in 0 until listArticles.length()) {
                    val articleJson = listArticles.getJSONObject(i)
                    val articleObj = Article (articleJson.getString("title"),
                        articleJson.getString("author"),
                        articleJson.getString("description"),
                        articleJson.getString("url"),
                        articleJson.getString("urlToImage"),
                        articleJson.getString("publishedAt"),
                        articleJson.getString("content"),
                        thisSource)
                    //Log.d(TAG, articleObj.toString())
                    theseArticles.add(articleObj)
                    //aa.notifyDataSetChanged()

                    //supportFragmentManager.beginTransaction().detach(listFragment!!).attach(listFragment!!).commit()
                }
                listFragment!!.showArticles(theseArticles)

            },
            Response.ErrorListener {
                //Toast.makeText(this, "That didn't work!", Toast.LENGTH_SHORT).show()
                Log.d(TAG,"didn't work : "+uriContentSources+ thisSource!!.id+"&page="+thisPage)
            }
        ){
            override fun getHeaders(): Map<String, String> {
                val headers: MutableMap<String, String> =
                    HashMap()
                headers["User-agent"] = "Mozilla/5.0"
                return headers
            }
        }




        queue.add(stringReq)
    }


    override fun onArticleSelected(article: Article) {
        val detailsFragment =
            DetailsFragment.newInstance(article)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, detailsFragment, "articleDetails")
            .addToBackStack(null)
            .commit()

    }



}