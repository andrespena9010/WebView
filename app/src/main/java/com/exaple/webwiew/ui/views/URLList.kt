package com.exaple.webwiew.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.exaple.webwiew.navigation.Views
import com.exaple.webwiew.ui.viewmodels.WebViewModel
import com.exaple.webwiew.ui.viewmodels.WebViewModelClass
import com.exaple.webwiew.utils.URLs

@Composable
fun URLList(
    nav: NavHostController,
    viewModel: WebViewModelClass = WebViewModel
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPaddings ->
        LazyColumn (
            modifier = Modifier
                .padding( innerPaddings )
                .padding( start = 20.dp )
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ){
            items ( URLs ){ url ->

                var navigation by remember { mutableStateOf( false ) }

                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Text("Permitir navegacion -> ")

                    RadioButton(
                        selected = navigation,
                        onClick = { navigation = !navigation }
                    )

                    Text(
                        text = url,
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    viewModel.setUrl( url, navigation)
                                    nav.navigate( Views.WebViewCompose )
                                }
                            )
                    )
                }
            }
        }
    }

}