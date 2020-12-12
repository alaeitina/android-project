package com.centrale.thedailysorcerer

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import layout.Article


class DetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_details, container, false)

        val txtTitle:TextView = view.findViewById(R.id.txtTitle)
        val txtAuthor:TextView = view.findViewById(R.id.txtAuthor)
        val txtDate:TextView = view.findViewById(R.id.txtDate)
        val btnClose:Button = view.findViewById(R.id.close)

        val article = arguments!!.getParcelable<Article>("article")

        txtTitle.setText(article!!.title)
        txtAuthor.setText(article.author)
        txtDate.setText(article.date)

        btnClose.setOnClickListener {
            activity?.supportFragmentManager?.popBackStackImmediate()
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