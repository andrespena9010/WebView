package com.exaple.webwiew.ui.views

import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
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
            AndroidView(
                factory = { ctx ->
                    viewModel.go( context = ctx )
                }
            )
        }
    }
}