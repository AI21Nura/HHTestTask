package com.ainsln.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainsln.core.common.result.DataResult
import com.ainsln.core.data.repository.offer.OfferRepository
import com.ainsln.core.data.repository.vacancy.VacancyRepository
import com.ainsln.core.model.ShortVacancy
import com.ainsln.core.ui.state.UiState
import com.ainsln.core.ui.state.toUiState
import com.ainsln.core.ui.utils.IntentManager
import com.ainsln.feature.search.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.max

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val intentManager: IntentManager,
    private val offerRepository: OfferRepository,
    private val vacancyRepository: VacancyRepository
) : ViewModel() {

    private val vacanciesResult: MutableStateFlow<UiState<List<ShortVacancy>>> = MutableStateFlow(UiState.Loading)

    private val _snackbarMsg: MutableStateFlow<String?> = MutableStateFlow(null)
    val snackbarMsg: StateFlow<String?> = _snackbarMsg

    private val _uiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    val isRefreshing = MutableStateFlow(true)

    init {
        loadUiState()
    }

    fun loadUiState(){
        loadVacancies()
        combine(offerRepository.getOffers(), vacanciesResult) { offers, vacancies ->
            val numOfMoreVacancies = if (vacancies is UiState.Success) max(0, vacancies.data.size - 3) else 0
            _uiState.update {
                it.copy(
                    offersState = offers.toUiState(),
                    vacanciesState = vacancies,
                    numberOfMoreVacancies = numOfMoreVacancies,
                )
            }
            isRefreshing.value = offers is DataResult.Loading || vacancies is UiState.Loading
        }.launchIn(viewModelScope)
    }

    fun onSearchQueryChange(value: String){
        _uiState.update { it.copy(searchQuery = value) }
    }

    fun loadVacancies() {
        viewModelScope.launch {
            vacancyRepository.getVacancies().map { it.toUiState() }
                .collectLatest { vacancies ->
                    vacanciesResult.update { vacancies }
                }
        }
    }

    fun changeVacanciesScreen(showMoreVacancies: Boolean){
        _uiState.update { it.copy(showExpandedVacanciesScreen = showMoreVacancies) }
    }

    fun showSnackbarMsg(msg: String?){
        _snackbarMsg.value = msg
    }

    fun openOfferLink(link: String, errorMsg: String){
        val success = intentManager.openWebPage(link)
        if (!success) _snackbarMsg.update { errorMsg }
    }

    fun toggleFavorite(vacancy: ShortVacancy){
        viewModelScope.launch {
            vacancyRepository.updateFavoriteStatus(vacancy.id, !vacancy.isFavorite)
        }
    }

    companion object {
        const val COMPACT_VACANCIES_LIST_SIZE = 3
    }

}
