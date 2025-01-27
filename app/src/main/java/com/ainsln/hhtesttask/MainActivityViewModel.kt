package com.ainsln.hhtesttask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainsln.core.common.result.DataResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class MainActivityViewModel : ViewModel() {

    val favoritesNumber: StateFlow<Int> = getFavoritesNumber().map { result ->
        if (result is DataResult.Success) result.data else 0
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0
    )

    //TEMPORARY
    private fun getFavoritesNumber(): Flow<DataResult<Int>>{
        return flow {
            delay(500)
            emit(DataResult.Success(4))
            delay(2000)
            emit(DataResult.Success(12))
        }.onStart { DataResult.Loading }
    }
}
