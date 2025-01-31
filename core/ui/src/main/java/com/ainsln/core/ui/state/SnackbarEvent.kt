package com.ainsln.core.ui.state

sealed interface UiEvent {
    data object OffersError : UiEvent
    data object IntentError : UiEvent
}
