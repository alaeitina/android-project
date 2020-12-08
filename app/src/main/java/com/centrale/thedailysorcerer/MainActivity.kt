package com.centrale.thedailysorcerer


import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val MainActivityTAG:String = "Main Activity"
    val uriListSources:String = "https://newsapi.org/v2/sources?apiKey=cfe66a38eadc47448a0eb945629ba205&language=fr"
    val uriContentSources:String = "https://newsapi.org/v2/everything?apiKey=cfe66a38eadc47448a0eb945629ba205&language=fr&sources=google-news-fr"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        getContent()
        
    }

    fun showAlertDialog(){
        val builder: AlertDialog.Builder?= DialogActivity@this?.let {
            AlertDialog.Builder(it)
        }
        builder?.setMessage("La connexion n'a pas pu être établie. Vérifiez votre connexion à l'internet mondial.")
            ?.setTitle("Echec de connexion")
            ?.setPositiveButton("Réessayer",
                DialogInterface.OnClickListener({dialog, which ->
                    getContent()
                }))
            ?.setNegativeButton("Quitter", 
                DialogInterface.OnClickListener({dialog, which ->  
                    finish()
                }))
        val dialog: AlertDialog? = builder?.create()
        dialog?.show()
    }
    
    fun getContent() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)

        // Request a string response from the provided URL.
        val stringReq : StringRequest = object : StringRequest(

            Request.Method.GET, uriListSources,
            Response.Listener<String> { response ->

                var strResp = response.toString()
                Log.d("3-Fetched Data", strResp)
                val result = JSONObject(strResp)
                val listSources: JSONArray = result.getJSONArray("sources")
                val allSourcesList: ArrayList<Source> = arrayListOf<Source>()

                for (i in 0 until listSources.length()) {
                    val sourceJson = listSources.getJSONObject(i)
                    val sourceObj = Source(sourceJson.getString("id"), sourceJson.getString("name"))
                    allSourcesList.add(sourceObj)
                }

                val monIntent = Intent(this, ListActivity::class.java)
                startActivity(monIntent)
            },
            Response.ErrorListener {
                //Toast.makeText(this, "That didn't work!", Toast.LENGTH_SHORT).show()
                Log.d("Fetched Data","That didn't work!")
                showAlertDialog()
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


}