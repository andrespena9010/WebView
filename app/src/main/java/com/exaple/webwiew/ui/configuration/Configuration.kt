package com.exaple.webwiew.ui.configuration

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.Log

fun get4KContext( realContext: Context ): Context {

    val density4K = DisplayMetrics.DENSITY_XHIGH
    val heightPX = 2160
    val widthPX = 3840
    val densityScale = density4K / 160f

    val config = Configuration( realContext.resources.configuration ).apply {
        screenHeightDp = ( heightPX / densityScale ).toInt()
        screenWidthDp = ( widthPX / densityScale ).toInt()
        smallestScreenWidthDp = minOf(screenWidthDp, screenHeightDp)
        densityDpi = densityDpi
    }

    val metrics = DisplayMetrics().apply {
        heightPixels = heightPX
        widthPixels = widthPX
        densityDpi = density4K
        density = densityScale
        xdpi = density4K.toFloat()
        ydpi = density4K.toFloat()
    }

    val context4k = realContext.createConfigurationContext(config)
    context4k.resources.displayMetrics.setTo( metrics )

    Log.d("CAILO", "Resolución personalizada: \n" +
            "alto px: ${context4k.resources.displayMetrics.heightPixels},\n" +
            "ancho px: ${context4k.resources.displayMetrics.widthPixels},\n" +
            "densidad: ${context4k.resources.displayMetrics.density}")

    return context4k
}

/*
val context4k = object : ContextWrapper( applicationContext ) {

    val density4K = DisplayMetrics.DENSITY_XHIGH
    val heightPX = 2160
    val widthPX = 3840
    val densityScale = density4K / 160f

    override fun getResources(): Resources {
        val res = super.getResources()
        val config = Configuration(res.configuration)
        val metrics = DisplayMetrics().apply {
            heightPixels = heightPX
            widthPixels = widthPX
            densityDpi = density4K
            density = densityScale
            xdpi = density4K.toFloat()
            ydpi = density4K.toFloat()
        }
        config.densityDpi = metrics.densityDpi
        config.screenHeightDp = ( heightPX / densityScale ).toInt()
        config.screenWidthDp = ( widthPX / densityScale ).toInt()
        config.smallestScreenWidthDp = ( widthPX / densityScale ).toInt()
        val newContext = createConfigurationContext(config)
        newContext.resources.displayMetrics.setTo(metrics)
        return newContext.resources
    }
}*/
