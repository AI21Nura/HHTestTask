package com.ainsln.feature.search.state

import com.ainsln.core.model.Offer
import com.ainsln.core.model.ShortVacancy
import com.ainsln.core.ui.state.UiState

data class SearchUiState(
    val offersState: UiState<List<Offer>> = UiState.Loading,
    val vacanciesState: UiState<List<ShortVacancy>> = UiState.Loading,
    val numberOfMoreVacancies: Int = 0,
    val showExpandedVacanciesScreen: Boolean = false,
    val searchQuery: String = ""
)
