package com.ainsln.feature.favorite

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ainsln.core.common.result.AppException
import com.ainsln.core.designsystem.component.SecondaryText
import com.ainsln.core.designsystem.icon.AppIcons
import com.ainsln.core.designsystem.theme.HHTestTaskTheme
import com.ainsln.core.model.ShortVacancy
import com.ainsln.core.ui.component.ErrorBlock
import com.ainsln.core.ui.component.PlaceholderScreen
import com.ainsln.core.ui.component.vacancy.ShimmerVacancyList
import com.ainsln.core.ui.component.vacancy.VacancyLazyList
import com.ainsln.core.ui.state.UiState
import com.ainsln.feature.favorites.R
import com.ainsln.core.ui.R as coreR

@Composable
internal fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    FavoritesScreenContent(uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FavoritesScreenContent(
    uiState: UiState<List<ShortVacancy>>,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp)
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.favorite_title),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            when (uiState) {
                is UiState.Loading -> ShimmerVacancyList(Modifier.padding(contentPadding))
                is UiState.Failure -> {
                    ErrorBlock(
                        e = uiState.e,
                        compact = false,
                        onRetryClick = {},
                        modifier = Modifier.fillMaxHeight(0.6f)
                    )
                }
                is UiState.Success -> {
                    FavoritesList(
                        favorites = uiState.data,
                        modifier = Modifier.padding(contentPadding)
                    )
                }
            }
        }
    }
}

@Composable
internal fun FavoritesList(
    favorites: List<ShortVacancy>,
    modifier: Modifier = Modifier
) {
    if (favorites.isNotEmpty()) {
        VacancyLazyList(
            vacancies = favorites,
            header = {
                SecondaryText(
                    pluralStringResource(
                        coreR.plurals.vacancies_number,
                        favorites.size,
                        favorites.size
                    )
                )
            },
            modifier = modifier
        )
    } else {
        PlaceholderScreen(
            text = stringResource(R.string.empty_favorites_placeholder),
            icon = AppIcons.StarFavorite,
            subtext = stringResource(R.string.empty_favorites_subtext),
            modifier = Modifier.fillMaxHeight(0.7f)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FavoritesScreenContentSuccessPreview() {
    HHTestTaskTheme {
        Surface(Modifier.fillMaxSize()) {
            FavoritesScreenContent(UiState.Success(PreviewData.vacancies))
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FavoritesScreenContentEmptyPreview() {
    HHTestTaskTheme {
        Surface(Modifier.fillMaxSize()) {
            FavoritesScreenContent(UiState.Success(emptyList()))
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FavoritesScreenContentLoadingPreview() {
    HHTestTaskTheme {
        Surface(Modifier.fillMaxSize()) {
            FavoritesScreenContent(UiState.Loading)
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FavoritesScreenContentErrorPreview() {
    HHTestTaskTheme {
        Surface(Modifier.fillMaxSize()) {
            FavoritesScreenContent(UiState.Failure(AppException.DatabaseError("")))
        }
    }
}
