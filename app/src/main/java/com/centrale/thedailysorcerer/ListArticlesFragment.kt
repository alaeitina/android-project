package com.centrale.thedailysorcerer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import eu.corellis.centrale.activitylifecycle.CustomAdapter
import layout.Article


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListNewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListNewsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    /*private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }*/


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d(TAG, dataset.size.toString())
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_list_news, container, false)

        Log.d(TAG, "viewAdapter being init")
        viewManager = LinearLayoutManager(activity)
        viewAdapter = CustomAdapter(dataset)

        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

        //val context = view.context
        /*val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = CustomAdapter(dataset)*/
        return view


    }

    companion object {
        @JvmStatic
        fun newInstance(/*param1: String, param2: String*/) = ListArticlesFragment()
    }

    fun clearArticles() {
        dataset.clear()
    }


}