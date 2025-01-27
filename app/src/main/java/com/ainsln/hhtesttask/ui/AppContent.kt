package com.ainsln.hhtesttask.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ainsln.feature.favorite.navigation.favoritesDestination
import com.ainsln.feature.search.navigation.SearchDestination
import com.ainsln.feature.search.navigation.searchDestination
import com.ainsln.hhtesttask.MainActivityViewModel
import com.ainsln.hhtesttask.ui.navigation.AppNavigationBar
import com.ainsln.hhtesttask.ui.placeholder.chatDestination
import com.ainsln.hhtesttask.ui.placeholder.profileDestination
import com.ainsln.hhtesttask.ui.placeholder.responseDestination

@Composable
fun AppContent(
    viewModel: MainActivityViewModel
) {
    val favoritesNumber by viewModel.favoritesNumber.collectAsState()
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            AppNavigationBar(navController, favoritesNumber)
        }
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = SearchDestination
            ) {
                searchDestination(
                    showSnackbarMsg = { msg ->
                        snackbarHostState.showSnackbar(
                            message = msg,
                            duration = SnackbarDuration.Short,
                            withDismissAction = true
                        )
                    }
                )
                favoritesDestination()
                responseDestination()
                chatDestination()
                profileDestination()
            }
        }
    }
}



