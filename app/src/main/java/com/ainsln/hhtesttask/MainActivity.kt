package com.ainsln.hhtesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.ainsln.core.designsystem.theme.HHTestTaskTheme
import com.ainsln.hhtesttask.ui.AppContent

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HHTestTaskTheme {
                AppContent(viewModel)
            }
        }
    }
}

