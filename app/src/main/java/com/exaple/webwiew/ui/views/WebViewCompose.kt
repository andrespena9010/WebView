package com.exaple.webwiew.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.exaple.webwiew.ui.viewmodels.WebViewModel
import com.exaple.webwiew.ui.viewmodels.WebViewModelClass

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewCompose(
    nav: NavHostController,
    viewModel: WebViewModelClass = WebViewModel
) {

    val navigation by viewModel.navigation.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPaddings ->
        Box (
            modifier = Modifier
                .padding( innerPaddings )
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){

            if ( !navigation ){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(1f)
                        .pointerInput( Unit ){
                            detectTapGestures(
                                onPress = {
                                    nav.popBackStack()
                                }
                            )
                        }
                )
            }

            AndroidView(
                factory = { ctx ->
                    viewModel.go( ctx )
                }
            )
        }
    }
}