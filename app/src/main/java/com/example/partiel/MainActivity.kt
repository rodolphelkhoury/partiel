package com.example.partiel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.partiel.ui.theme.PartielTheme
import com.example.partiel.viewModels.WelcomePageViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val welcomePageViewModel  = ViewModelProvider(this)[WelcomePageViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            PartielTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    WelcomePage(
                        viewModel = welcomePageViewModel
                    )
                }
            }
        }
    }
}
