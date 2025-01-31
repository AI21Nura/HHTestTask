package com.ainsln.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainsln.core.data.repository.vacancy.VacancyRepository
import com.ainsln.core.model.ShortVacancy
import com.ainsln.core.ui.state.UiState
import com.ainsln.core.ui.state.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
   private val vacancyRepository: VacancyRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<ShortVacancy>>> = MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadFavorites()
    }

    fun loadFavorites() {
        viewModelScope.launch {
            vacancyRepository.getAllFavorites().map { it.toUiState() }
                .collectLatest { vacancies ->
                    _uiState.update { vacancies }
                }
        }
    }

    fun toggleFavorite(vacancy: ShortVacancy){
        viewModelScope.launch {
            vacancyRepository.updateFavoriteStatus(vacancy.id, !vacancy.isFavorite)
        }
    }
}
