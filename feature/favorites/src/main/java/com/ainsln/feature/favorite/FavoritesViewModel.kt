package com.ainsln.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ainsln.core.common.result.DataResult
import com.ainsln.core.model.ShortVacancy
import com.ainsln.core.ui.state.UiState
import com.ainsln.core.ui.state.toUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class FavoritesViewModel : ViewModel() {

    val uiState = getFavoritesFlow().map { it.toUiState() }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UiState.Loading
    )

    private fun getFavoritesFlow(): Flow<DataResult<List<ShortVacancy>>> {
        return flow<DataResult<List<ShortVacancy>>> {
            delay(1000)
            emit(DataResult.Success(PreviewData.vacancies))
        }.onStart { emit(DataResult.Loading) }
    }

    companion object{
        val FACTORY : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                FavoritesViewModel()
            }
        }
    }

}
