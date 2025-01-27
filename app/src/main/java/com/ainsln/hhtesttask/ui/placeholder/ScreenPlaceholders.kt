package com.ainsln.hhtesttask.ui.placeholder

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ainsln.core.ui.component.PlaceholderBlock
import kotlinx.serialization.Serializable
import com.ainsln.core.designsystem.R as R

@Serializable data object ResponseDestination
@Serializable data object ChatDestination
@Serializable data object ProfileDestination

fun NavGraphBuilder.responseDestination(){
    composable<ResponseDestination> {
        PlaceholderBlock(
            text = "Откликов пока нет",
            subtext = "Ваши отклики будут отображаться здесь, как только вы начнете искать работу",
            icon = ImageVector.vectorResource(R.drawable.ic_response),
            compact = false,
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun NavGraphBuilder.chatDestination(){
    composable<ChatDestination> {
        PlaceholderBlock(
            text = "Пока нет сообщений",
            subtext = "Работодатели свяжутся с вами здесь, как только вы откликнетесь на их вакансии",
            icon = ImageVector.vectorResource(R.drawable.ic_chat),
            compact = false,
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun NavGraphBuilder.profileDestination(){
    composable<ProfileDestination> {
        PlaceholderBlock(
            text = "Профиль не заполнен",
            subtext = "Расскажите о себе — это увеличит ваши шансы найти работу",
            icon = ImageVector.vectorResource(R.drawable.ic_profile),
            compact = false,
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun NavController.navigateToResponses(navOptions: NavOptions){
    navigate(route = ResponseDestination, navOptions)
}

fun NavController.navigateToChats(navOptions: NavOptions){
    navigate(route = ChatDestination, navOptions)
}

fun NavController.navigateToProfile(navOptions: NavOptions){
    navigate(route = ProfileDestination, navOptions)
}
