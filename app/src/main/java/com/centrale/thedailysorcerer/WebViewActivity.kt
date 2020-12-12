package com.centrale.thedailysorcerer

import android.annotation.TargetApi
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity


class WebViewActivity : AppCompatActivity() {

    private lateinit var webView:WebView
    private lateinit var loader:ProgressBar

    val TAG:String = "WebView Activity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webView = findViewById(R.id.web_view)
        loader = findViewById(R.id.progressBar)

        val i = intent
        val urlArticle:String = i.getStringExtra("urlArticle") as String
        webView.webViewClient = WebViewClient()

        webView.loadUrl(urlArticle)

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, weburl: String) {
                loader.visibility = View.GONE
            }
        }

    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

}