package com.exaple.webwiew

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.exaple.webwiew.navigation.Navigation
import com.exaple.webwiew.ui.theme.WebWiewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WebWiewTheme {
                Navigation()
            }
        }
    }
}