package com.exaple.webwiew

import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.ComposeView
import com.exaple.webwiew.navigation.Navigation
import com.exaple.webwiew.ui.configuration.get4KContext
import com.exaple.webwiew.ui.theme.WebWiewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val composeView = ComposeView( get4KContext( applicationContext ) ).apply {
            setContent {
                WebWiewTheme {
                    Navigation()
                }
            }
        }

        setContentView( composeView )

        /*setContent {
            WebWiewTheme {
                Navigation()
            }
        }*/
    }
}

