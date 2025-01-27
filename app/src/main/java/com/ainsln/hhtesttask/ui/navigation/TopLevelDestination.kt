package com.ainsln.hhtesttask.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ainsln.feature.favorite.navigation.FavoritesDestination
import com.ainsln.feature.search.navigation.SearchDestination
import com.ainsln.hhtesttask.R
import com.ainsln.hhtesttask.ui.placeholder.ChatDestination
import com.ainsln.hhtesttask.ui.placeholder.ProfileDestination
import com.ainsln.hhtesttask.ui.placeholder.ResponseDestination
import com.ainsln.core.designsystem.R.drawable as icons

import kotlin.reflect.KClass

enum class TopLevelDestination(
    @StringRes val titleResId: Int,
    @DrawableRes val iconResId: Int,
    val route: KClass<*>
) {
    SEARCH(
        titleResId = R.string.search_item,
        iconResId = icons.ic_search,
        route = SearchDestination::class
    ),
    FAVORITES(
        titleResId = R.string.favorites_item,
        iconResId = icons.ic_heart_inactive,
        route = FavoritesDestination::class
    ),
    RESPONSE(
        titleResId = R.string.response_item,
        iconResId = icons.ic_response,
        route = ResponseDestination::class
    ),
    CHAT(
        titleResId = R.string.chat_item,
        iconResId = icons.ic_chat,
        route = ChatDestination::class
    ),
    PROFILE(
        titleResId = R.string.profile_item,
        iconResId = icons.ic_profile,
        route = ProfileDestination::class
    )
}
