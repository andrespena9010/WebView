package com.exaple.webwiew.ui.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModel

@SuppressLint("StaticFieldLeak")
open class WebViewModelClass(): ViewModel(){

    private lateinit var webView: WebView
    private lateinit var url: String

    fun setUrl( url: String ){
        this.url = url
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun go(context: Context ): WebView {

        webView = WebView( context )

        webView.webViewClient = WebViewClient()

        webView.loadUrl( url )

        return webView
    }

}

@SuppressLint("StaticFieldLeak")
object WebViewModel: WebViewModelClass()