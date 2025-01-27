package com.ainsln.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainsln.core.common.result.DataResult
import com.ainsln.core.model.ShortVacancy
import com.ainsln.core.ui.state.UiState
import com.ainsln.core.ui.state.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
) : ViewModel() {

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
}
