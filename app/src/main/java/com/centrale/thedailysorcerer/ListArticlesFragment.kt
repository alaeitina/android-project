package com.centrale.thedailysorcerer

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import eu.corellis.centrale.activitylifecycle.CustomAdapter
import layout.Article


class ListArticlesFragment : Fragment() {

    val TAG:String = "List Articles Fragment"

    //var theseArticles: ArrayList<Article?> = arrayListOf<Article?>()


    //private lateinit var recyclerView: RecyclerView
    //var viewAdapter: RecyclerView.Adapter<*>? = null
    //var viewManager: RecyclerView.LayoutManager? = null
    //private lateinit var dataset: Array<String>
    var dataset: ArrayList<Article?> = arrayListOf<Article?>()

    //val adapter: CustomAdapter? = null

    //var listener

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d(TAG, dataset.size.toString())
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_list_articles, container, false)


        //viewManager = LinearLayoutManager(context)
        //viewAdapter = CustomAdapter(dataset)

        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = LinearLayoutManager(context)

            // specify an viewAdapter (see also next example)
            adapter = CustomAdapter(dataset)

        }

        Log.d(TAG, "Recycler View has been set up $recyclerView!!.toString()")
        //val context = view.context
        /*val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = CustomAdapter(dataset)*/
        return view


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "context : $context")
    }


    fun addArticle(article: Article) {
        dataset.add(article)
        Log.d(TAG, recyclerView.toString())
        //recyclerView.adapter!!.notifyDataSetChanged()
    }




    companion object {
        @JvmStatic
        fun newInstance() = ListArticlesFragment()
        lateinit var recyclerView: RecyclerView
    }

    fun clearArticles() {
        dataset.clear()
    }

    fun showArticles(newArticles: ArrayList<Article?>) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = CustomAdapter(newArticles)
        dataset = newArticles
        Log.d(TAG, dataset.toString())
    }


}