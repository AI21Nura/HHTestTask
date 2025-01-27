package com.ainsln.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ainsln.feature.search.SearchScreen
import kotlinx.serialization.Serializable

@Serializable
data object SearchDestination

fun NavGraphBuilder.searchDestination(
    showSnackbarMsg: suspend (String) -> Unit
){
    composable<SearchDestination> {
        SearchScreen(
            showSnackbarMsg = showSnackbarMsg
        )
    }
}

fun NavController.navigateToSearch(navOptions: NavOptions){
    navigate(route = SearchDestination, navOptions)
}
