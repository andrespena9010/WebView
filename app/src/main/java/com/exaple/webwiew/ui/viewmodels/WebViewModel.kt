package com.exaple.webwiew.ui.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


@SuppressLint("StaticFieldLeak")
open class WebViewModelClass(): ViewModel(){

    private lateinit var webView: WebView
    private lateinit var url: String

    private val _navigation = MutableStateFlow( false )
    val navigation: StateFlow<Boolean> = _navigation.asStateFlow()

    fun setUrl(
        url: String,
        navigation: Boolean
        ){
        _navigation.update { navigation }
        this.url = url
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun getWebView( context: Context ): WebView {

        webView = WebView( context )

        webView.clearCache(true)

        webView.webViewClient = object: WebViewClient(){
            override fun onLoadResource(view: WebView?, urlResource: String?) {
                view?.evaluateJavascript(
                    """
                        (function() {
                            metaViewport = document.createElement('meta');
                            metaViewport.name = 'viewport';
                            metaViewport.content = 'width=3840, height=2160';
                            document.head.appendChild(metaViewport);
                        })();
                        """.trimIndent(),
                    null
                )
                super.onLoadResource(view, urlResource)
            }
        }

        webView.settings.apply {
            javaScriptEnabled = true
            useWideViewPort = true
            loadWithOverviewMode = true
            domStorageEnabled = true
            allowFileAccess = true
            cacheMode = WebSettings.LOAD_DEFAULT
            textZoom = 100
            builtInZoomControls = true
            displayZoomControls = false
            setSupportZoom(true)
            loadsImagesAutomatically = true
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
            mediaPlaybackRequiresUserGesture = false

        }

        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)
        cookieManager.acceptThirdPartyCookies(webView)

        webView.loadUrl( url )

        return webView
    }

}

@SuppressLint("StaticFieldLeak")
object WebViewModel: WebViewModelClass()