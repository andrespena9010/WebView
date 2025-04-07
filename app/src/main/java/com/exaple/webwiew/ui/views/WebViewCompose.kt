package com.exaple.webwiew.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.exaple.webwiew.ui.viewmodels.WebViewModel
import com.exaple.webwiew.ui.viewmodels.WebViewModelClass

@Composable
fun WebViewCompose(
    nav: NavHostController,
    viewModel: WebViewModelClass = WebViewModel
) {

    /*var slideStart by remember { mutableFloatStateOf(0f) }
    var slideEnd by remember { mutableFloatStateOf(0f) }*/

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPaddings ->
        Box (
            modifier = Modifier
                .padding( innerPaddings )
                .fillMaxSize()
                /*.pointerInput( Unit ){
                    detectDragGestures(
                        onDragStart = { offset ->
                            slideStart = offset.x
                            txt = offset.x.toString()
                        },
                        onDrag = { pointer, offset ->
                            slideEnd += offset.x
                            txt = offset.x.toString()
                        },
                        onDragEnd = {
                            if ( slideEnd > 0 ) nav.popBackStack()
                        }
                    )
                }*/,
            contentAlignment = Alignment.Center
        ){
            AndroidView(
                factory = { context ->
                    viewModel.go( context = context )
                }
            )
        }
    }
}