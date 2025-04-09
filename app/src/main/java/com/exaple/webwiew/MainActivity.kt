package com.exaple.webwiew

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.exaple.webwiew.navigation.Navigation
import com.exaple.webwiew.ui.theme.WebWiewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WebWiewTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPaddings ->
                    Box(
                        modifier = Modifier
                            .padding(innerPaddings)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Navigation()
                    }
                }
            }
        }
    }
}