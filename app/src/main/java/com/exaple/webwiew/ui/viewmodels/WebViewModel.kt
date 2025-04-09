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
    fun go( context: Context ): WebView {

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

            // Habilita JavaScript (desactivado por defecto por seguridad)
            javaScriptEnabled = true

            // Usa un viewport "amplio", como un navegador de escritorio. Necesario para que el meta viewport funcione bien
            useWideViewPort = true

            // Ajusta el contenido a la pantalla al cargar la página
            loadWithOverviewMode = true

            // Permite el almacenamiento DOM (como localStorage)
            domStorageEnabled = true

            // Permite el acceso a archivos locales (file:// URLs)
            allowFileAccess = true

            // Habilita la caché de la app (necesita setAppCachePath)
            cacheMode = WebSettings.LOAD_DEFAULT

            // Nivel de zoom inicial para texto (porcentaje)
            textZoom = 100

            // Modo de zoom, permite control de gestos de zoom
            builtInZoomControls = true

            // Oculta los controles de zoom nativos (aunque el zoom sigue funcionando con gestos)
            displayZoomControls = false

            // Habilita el soporte para zoom
            setSupportZoom(true)

            // Habilita la carga automática de imágenes
            loadsImagesAutomatically = true

            // Habilita o desactiva la carga de contenido mixto (http dentro de https)
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

            // Layout: puede ser NARROW_COLUMNS, NORMAL, SINGLE_COLUMN, TEXT_AUTOSIZING
            layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING

            // Define si el contenido puede acceder al clipboard
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