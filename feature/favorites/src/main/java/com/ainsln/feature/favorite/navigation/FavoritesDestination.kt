package com.ainsln.feature.favorite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ainsln.feature.favorite.FavoritesScreen
import kotlinx.serialization.Serializable

@Serializable
data object FavoritesDestination

fun NavGraphBuilder.favoritesDestination(){
    composable<FavoritesDestination> {
        FavoritesScreen()
    }
}

fun NavController.navigateToFavorites(navOptions: NavOptions){
    navigate(route = FavoritesDestination, navOptions)
}
