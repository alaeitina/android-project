package com.centrale.thedailysorcerer

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    //var dataset: ArrayList<Article?> = arrayListOf<Article?>()
    private lateinit var bundleState:Bundle


    //val adapter: CustomAdapter? = null

    //var listener



    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is CustomAdapter.OnArticleSelectedListener) {
            aListener = context
        }
        if (context is CustomAdapter.OnBottomReachedListener) {
            bListener = context
        }
        else {
            Log.d(TAG, "aListener is not")
            throw RuntimeException(
                context.toString()
                        + " must implement OnListFragmentInteractionListener"
            )
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d(TAG, dataset.size.toString())
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_list_articles, container, false)


        viewManager = LinearLayoutManager(context)
        viewAdapter = CustomAdapter(dataset, aListener, bListener)

        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
        retainInstance = true
        //val context = view.context
        /*val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = CustomAdapter(dataset)*/
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    bListener.onBottomReached()
                }
            }
        })

        return view


    }


    companion object {
        @JvmStatic
        fun newInstance() = ListArticlesFragment()
        lateinit var viewManager: LinearLayoutManager
        lateinit var viewAdapter: CustomAdapter
        lateinit var recyclerView: RecyclerView
        private lateinit var aListener: CustomAdapter.OnArticleSelectedListener
        private lateinit var bListener: CustomAdapter.OnBottomReachedListener

        private lateinit var bundleState:Bundle
        var dataset: ArrayList<Article?> = arrayListOf<Article?>()
        //private var listState:Parcelable? = null
    }

    fun clearArticles() {
        dataset.clear()
    }

    fun showArticles(newArticles: ArrayList<Article?>) {
        //recyclerView.layoutManager = LinearLayoutManager(activity)
        Log.d(TAG, "old : ${dataset.size}")
        for(i in 0 until newArticles.size){
            dataset.add(newArticles[i])
        }
        //dataset = newArticles
        recyclerView.adapter!!.notifyDataSetChanged()


        Log.d(TAG, dataset.toString())
    }


    override fun onPause() {
        //Log.d(TAG, "is on Pause")
        //Log.d(TAG, "dataset : $dataset")
        super.onPause()
        bundleState = Bundle()
        bundleState.putParcelableArrayList("state", dataset)
        Log.d(TAG, "dataset : $dataset")
    }

    override fun onResume() {
        Log.d(TAG, "on resume")
        super.onResume()
        if (this::bundleState.isInitialized) {
            Log.d(TAG, "dataset : $dataset")
            dataset = bundleState.getParcelableArrayList<Article>("state") as ArrayList<Article?>
            Log.d(TAG, "dataset : $dataset")
        }
    }


}