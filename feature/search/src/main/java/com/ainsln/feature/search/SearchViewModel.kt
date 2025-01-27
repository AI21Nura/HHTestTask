package com.ainsln.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainsln.core.common.result.AppException
import com.ainsln.core.common.result.DataResult
import com.ainsln.core.model.Offer
import com.ainsln.core.model.ShortVacancy
import com.ainsln.core.ui.state.UiState
import com.ainsln.core.ui.state.toUiState
import com.ainsln.feature.search.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.max

@HiltViewModel
class SearchViewModel @Inject constructor(
) : ViewModel() {

    private val vacanciesResult: MutableStateFlow<UiState<List<ShortVacancy>>> = MutableStateFlow(UiState.Loading)

    private val _snackbarMsg: MutableStateFlow<String?> = MutableStateFlow(null)
    val snackbarMsg: StateFlow<String?> = _snackbarMsg

    private val _uiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    init {
        initialUiState()
    }

    private fun initialUiState(){
        loadVacancies(true)
        combine(offersFlow(), vacanciesResult) { offers, vacancies ->
            val numOfMoreVacancies = if (vacancies is UiState.Success) max(0, vacancies.data.size - 3) else 0
            _uiState.update {
                it.copy(
                    offersState = offers.toUiState(),
                    vacanciesState = vacancies,
                    numberOfMoreVacancies = numOfMoreVacancies,
                )
            }
        }.launchIn(viewModelScope)
    }

    fun onSearchQueryChange(value: String){
        _uiState.update { it.copy(searchQuery = value) }
    }

    fun loadVacancies(error: Boolean = false) {
        viewModelScope.launch {
            vacanciesFlow(error).map { it.toUiState() }
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

    companion object {
        const val COMPACT_VACANCIES_LIST_SIZE = 3
    }

    //TEMPORARY
    private fun vacanciesFlow(error: Boolean = false) : Flow<DataResult<List<ShortVacancy>>>{
        return flow {
            delay(1000)
            if (error) emit(DataResult.Failure(AppException.NetworkError(code = 404, msg = "Ошибка при загрузке данных")))
            else emit(DataResult.Success(PreviewData.vacancies))
        }.onStart {
            emit(DataResult.Loading)
        }
    }

    //TEMPORARY
    private fun offersFlow() : Flow<DataResult<List<Offer>>>{
        return flow<DataResult<List<Offer>>> {
            delay(1000)
            emit(DataResult.Success(PreviewData.offers))
            //emit(DataResult.Failure(AppException.NetworkError(code = 404, msg = "Ошибка при загрузке данных")))
        }.onStart {
            emit(DataResult.Loading)
        }
    }

}
