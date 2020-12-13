package com.centrale.thedailysorcerer

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import eu.corellis.centrale.activitylifecycle.CustomAdapter
import layout.Article
import org.json.JSONArray
import org.json.JSONObject

class ListActivity : AppCompatActivity(), CustomAdapter.OnArticleSelectedListener, CustomAdapter.OnBottomReachedListener {

    val TAG:String = "ListActivity"

    //my key : cfe66a38eadc47448a0eb945629ba205
    //my second key : 00baf9a536cd4b70b7850fc8c241ea90

    val uriContentSources:String = "https://newsapi.org/v2/everything?apiKey=cfe66a38eadc47448a0eb945629ba205&language=fr&sources="

    var thisSource: Source? = null
    var thisPage:Int = 1

    var listFragment: ListArticlesFragment? = null
    var detailsFragment: DetailsFragment? = null
    var loading: Boolean = false;


    var listSources:ArrayList<Source> = arrayListOf<Source>()

    lateinit var queue: RequestQueue


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val i = intent
        listSources = i.getParcelableArrayListExtra<Source>("list") as ArrayList<Source>

        queue = Volley.newRequestQueue(this)


        if(savedInstanceState == null) {
            listFragment = ListArticlesFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment, ListArticlesFragment.newInstance())
                .commit()
        }

        thisSource = listSources.get(0)
        thisPage = 1
        listFragment?.clearArticles()
        getContent()



        /*val spinner = findViewById<View>(R.id.spinner_sources) as Spinner

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


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d(TAG, "No selected : $thisSource")
            }
        }*/
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.clear()
        for (i in 0 until listSources.size) {
            menu?.add(0, i, i, listSources[i].name)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        Log.d(TAG, item.itemId.toString())
        thisSource = listSources.get(item.itemId)
        Log.d(TAG, "$thisSource")
        thisPage = 1
        listFragment!!.clearArticles()
        if (detailsFragment != null) {
            /*supportFragmentManager.beginTransaction().remove(detailsFragment!!).commit()
            detailsFragment = null*/
            this.supportFragmentManager.popBackStackImmediate()
        }
        getContent()
        return true
    }

    fun getContent() {
        // Request a string response from the provided URL.
        if(loading)
            return

        loading = true;
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
                Log.d(TAG, "getContent $theseArticles")
                listFragment!!.showArticles(theseArticles)
                loading = false;

            },
            Response.ErrorListener {
                //Toast.makeText(this, "That didn't work!", Toast.LENGTH_SHORT).show()
                Log.d(TAG,"didn't work : "+uriContentSources+ thisSource!!.id+"&page="+thisPage)
                loading = false;
                Toast.makeText(this, "Vérifiez votre connexion.", Toast.LENGTH_LONG).show()
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
        detailsFragment = DetailsFragment.newInstance(article)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, detailsFragment!!, "articleDetails")
            .addToBackStack(null)
            .commit()

    }


    override fun onBottomReached() {
        if (loading){
            return
        }

        if(thisPage==5){
            Toast.makeText(this, "Vous avez chargé les 5 pages possibles.", Toast.LENGTH_SHORT).show()
            return
        }

        thisPage++
        Log.d(TAG, "$thisPage")
        getContent()
    }


}