package com.exaple.webwiew.ui.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.webkit.WebSettings
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
    fun go( context: Context ): WebView {

        /*val width = context.resources.displayMetrics.widthPixels
        val density = context.resources.displayMetrics.density
        var fraction = 1f

        if ( density == 2f && width >= 3840 ) fraction = 0.2f*/

        webView = WebView( context )

        webView.webViewClient = object: WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                webView.evaluateJavascript(
                    """
                    (function() {
                        
                        var metaViewport = document.querySelector('meta[name="viewport"]');
    
                        if (metaViewport) {
                            metaViewport.setAttribute('content', 'width=1300');
                        } else {
                            metaViewport = document.createElement('meta');
                            metaViewport.name = 'viewport';
                            metaViewport.content = 'width=1300';
                            document.head.appendChild(metaViewport);
                        }
                        
                    })();
                    """.trimIndent()
                    , null
                )
            }
        }

        webView.settings.apply {

            userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36"

            javaScriptEnabled = true // Habilita JavaScript
            domStorageEnabled = true // Habilita almacenamiento DOM
            cacheMode = WebSettings.LOAD_DEFAULT // Usa la caché si está disponible

            useWideViewPort = true // Usa un viewport amplio (como un navegador)
            loadWithOverviewMode = true // Escala el contenido al tamaño del WebView

            //builtInZoomControls = true // Habilita controles de zoom (pinch)
            displayZoomControls = false // Oculta los botones de zoom

            allowFileAccess = true // Permite acceso a archivos locales (¡cuidado con la seguridad!)
            allowContentAccess = true // Permite acceso a contenido multimedia

            javaScriptCanOpenWindowsAutomatically = true // Permite ventanas emergentes
            mediaPlaybackRequiresUserGesture = false // Permite autoplay de audio/video

            databaseEnabled = true // Habilita almacenamiento tipo base de datos

            loadsImagesAutomatically = true // Carga imágenes automáticamente
            blockNetworkImage = false // No bloquea imágenes cargadas por red
            blockNetworkLoads = false // No bloquea ningún recurso de red

            //setSupportZoom(true) // Permite hacer zoom
            setSupportMultipleWindows(false) // No soporta múltiples ventanas (popups)

            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW // Permite contenido http dentro de https (si es necesario)

        }

        webView.loadUrl( url )

        return webView
    }

}

@SuppressLint("StaticFieldLeak")
object WebViewModel: WebViewModelClass()