package com.centrale.thedailysorcerer

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import layout.Article


class DetailsFragment : Fragment() {

    var TAG = "DetailsFragment"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_details, container, false)

        val txtTitle:TextView = view.findViewById(R.id.txtTitle)
        val txtAuthor:TextView = view.findViewById(R.id.txtAuthor)
        val txtDate:TextView = view.findViewById(R.id.txtDate)
        val txtDescription:TextView = view.findViewById(R.id.txtDescription)
        val txtSource:TextView = view.findViewById(R.id.txtSource)
        val btnClose:Button = view.findViewById(R.id.close)
        val btnUrl:Button = view.findViewById(R.id.openurl)

        val article = arguments!!.getParcelable<Article>("article")

        txtTitle.setText(article!!.title)
        txtAuthor.setText(article.author)
        txtDate.setText(article.date?.substring(0, 10) + " " + article.date?.substring(11, 16))
        txtDescription.setText(article.description)
        txtSource.setText(article.source!!.name)


        btnClose.setOnClickListener {
            activity?.supportFragmentManager?.popBackStackImmediate()
        }

        btnUrl.setOnClickListener {
            val monIntent = Intent(activity, WebViewActivity::class.java).apply {
                putExtra("urlArticle", article.articleurl)
            }
            startActivity(monIntent)
        }

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(article: Article) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("article", article)
            }
        }
    }
}