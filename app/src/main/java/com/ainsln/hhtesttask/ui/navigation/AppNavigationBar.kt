package com.ainsln.hhtesttask.ui.navigation

import android.content.res.Configuration
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.ainsln.core.designsystem.component.NavItemBadge
import com.ainsln.core.designsystem.theme.Blue
import com.ainsln.core.designsystem.theme.HHTestTaskTheme
import com.ainsln.feature.favorite.navigation.navigateToFavorites
import com.ainsln.feature.search.navigation.navigateToSearch
import com.ainsln.hhtesttask.ui.placeholder.navigateToChats
import com.ainsln.hhtesttask.ui.placeholder.navigateToProfile
import com.ainsln.hhtesttask.ui.placeholder.navigateToResponses
import kotlin.reflect.KClass

@Composable
fun AppNavigationBar(
    navController: NavController,
    favoritesNumber: Int
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background
    ) {
        TopLevelDestination.entries.forEach { destination ->
            val isSelected = currentDestination.isRouteInHierarchy(destination.route)
            NavigationBarItem(
                selected = isSelected,
                onClick = { navController.navigateToTopDestination(destination) },
                label = { Text(stringResource(destination.titleResId)) },
                icon = {
                    BadgedBox(
                        badge = {
                            if (destination == TopLevelDestination.FAVORITES && favoritesNumber > 0) {
                                NavItemBadge(favoritesNumber.toString())
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(destination.iconResId),
                            contentDescription = stringResource(destination.titleResId),
                            tint = if (isSelected) Blue else Color.Unspecified,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent)
            )
        }
    }
}

internal fun NavController.navigateToTopDestination(destination: TopLevelDestination) {
    val navOptions = navOptions {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

    when (destination) {
        TopLevelDestination.SEARCH -> navigateToSearch(navOptions)
        TopLevelDestination.FAVORITES -> navigateToFavorites(navOptions)
        TopLevelDestination.RESPONSE -> navigateToResponses(navOptions)
        TopLevelDestination.CHAT -> navigateToChats(navOptions)
        TopLevelDestination.PROFILE -> navigateToProfile(navOptions)
    }
}

internal fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false


@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AppNavigationBarDarkPreview() {
    HHTestTaskTheme {
        AppNavigationBar(rememberNavController(), 0)
    }
}

@Composable
@Preview
private fun AppNavigationBarPreview() {
    HHTestTaskTheme {
        AppNavigationBar(rememberNavController(), 2)
    }
}
