package com.ainsln.feature.search

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ainsln.core.common.result.exception.AppException
import com.ainsln.core.designsystem.component.BigButton
import com.ainsln.core.designsystem.component.SecondaryText
import com.ainsln.core.designsystem.component.TextButton
import com.ainsln.core.designsystem.icon.AppIcons
import com.ainsln.core.designsystem.theme.HHTestTaskTheme
import com.ainsln.core.model.Offer
import com.ainsln.core.model.ShortVacancy
import com.ainsln.core.ui.component.ErrorBlock
import com.ainsln.core.ui.component.vacancy.EmptyVacanciesCard
import com.ainsln.core.ui.component.vacancy.ErrorVacancyCard
import com.ainsln.core.ui.component.vacancy.ShimmerVacancyList
import com.ainsln.core.ui.component.vacancy.VacancyCard
import com.ainsln.core.ui.component.vacancy.VacancyLazyList
import com.ainsln.core.ui.state.UiEvent
import com.ainsln.core.ui.state.UiState
import com.ainsln.feature.search.PreviewData.vacancies
import com.ainsln.feature.search.PreviewData.offers
import com.ainsln.feature.search.SearchViewModel.Companion.COMPACT_VACANCIES_LIST_SIZE
import com.ainsln.feature.search.component.OfferList
import com.ainsln.feature.search.component.SearchRow
import com.ainsln.feature.search.component.ShimmerOfferList
import com.ainsln.feature.search.state.SearchUiState
import com.ainsln.core.ui.R.plurals as plurals

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchScreen(
    showSnackbarMsg: suspend (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsState()
    val uiEvent by viewModel.uiEvent.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(uiEvent) {
        uiEvent?.let { event ->
            when(event){
                is UiEvent.OffersError -> showSnackbarMsg(context.getString(R.string.offers_error))
                is UiEvent.IntentError -> showSnackbarMsg(context.getString(R.string.activity_launch_error))
            }
            viewModel.sendEvent(null)
        }
    }
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = viewModel::loadUiState,
    ) {
        SearchScreenContent(
            uiState = uiState,
            onRetryClick = viewModel::loadVacancies,
            onSearchQueryChange = viewModel::onSearchQueryChange,
            onMoreVacanciesClick = { viewModel.changeVacanciesScreen(true) },
            backToMainScreen = { viewModel.changeVacanciesScreen(false) },
            onFavoriteClick = viewModel::toggleFavorite,
            openOfferLink = viewModel::openOfferLink
        )
    }
}

@Composable
internal fun SearchScreenContent(
    uiState: SearchUiState,
    onRetryClick: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onMoreVacanciesClick: () -> Unit,
    backToMainScreen: () -> Unit,
    onFavoriteClick: (ShortVacancy) -> Unit,
    openOfferLink: (String) -> Unit,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp)
){
    Column(Modifier.padding(contentPadding)) {
        Crossfade(
            targetState = uiState.showExpandedVacanciesScreen,
            label = "SearchScreens"
        ) { state ->
            if (state) {
                ExpandedVacanciesContent(
                    vacanciesState = uiState.vacanciesState,
                    searchQuery = uiState.searchQuery,
                    onSearchQueryChange = onSearchQueryChange,
                    backToMainScreen = backToMainScreen,
                    onFavoriteClick = onFavoriteClick
                )
            } else {
                MainSearchContent(
                    uiState = uiState,
                    onSearchQueryChange = onSearchQueryChange,
                    onRetryClick = onRetryClick,
                    onFavoriteClick = onFavoriteClick,
                    onMoreVacanciesClick = onMoreVacanciesClick,
                    openOfferLink = openOfferLink
                )
            }
        }
    }
}

@Composable
internal fun ExpandedVacanciesContent(
    vacanciesState: UiState<List<ShortVacancy>>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    backToMainScreen: () -> Unit,
    onFavoriteClick: (ShortVacancy) -> Unit,
    modifier: Modifier = Modifier
){
    BackHandler { backToMainScreen() }
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(top = 16.dp)
    ) {
        SearchRow(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            onSearchClick = {},
            leadingIcon = {
                IconButton(onClick = backToMainScreen) {
                    Icon(
                        imageVector = AppIcons.Back,
                        contentDescription = stringResource(R.string.back),
                        tint = MaterialTheme.colorScheme.outline
                    )
                }
            },
            placeholderText = stringResource(R.string.search_suitable_placeholder)
        )

        when(vacanciesState){
            is UiState.Loading -> ShimmerVacancyList()
            is UiState.Failure -> {
                ErrorBlock(
                    e = vacanciesState.e,
                    compact = false,
                    modifier = Modifier.fillMaxHeight(0.6f)
                )
            }
            is UiState.Success -> {
                VacancyLazyList(
                    header = {
                        Row(Modifier.padding(bottom = 8.dp)) {
                            SecondaryText(
                                pluralStringResource(
                                    plurals.vacancies_number,
                                    vacancies.size,
                                    vacancies.size
                                )
                            )
                            Spacer(Modifier.weight(1f))
                            TextButton(
                                text = stringResource(R.string.by_search_match),
                                onClick = {},
                                icon = AppIcons.Sort
                            )
                        }
                    },
                    vacancies = vacanciesState.data,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }
    }
}

@Composable
internal fun MainSearchContent(
    uiState: SearchUiState,
    onSearchQueryChange: (String) -> Unit,
    onMoreVacanciesClick: () -> Unit,
    onRetryClick: () -> Unit,
    onFavoriteClick: (ShortVacancy) -> Unit,
    openOfferLink: (String) -> Unit
){
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {
        SearchRow(
            value = uiState.searchQuery,
            onValueChange = onSearchQueryChange,
            onSearchClick = {},
            leadingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = AppIcons.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.fillMaxSize(0.8f)
                    )
                }
            },
            placeholderText = stringResource(R.string.search_placeholder),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OfferBlock(
            offersState = uiState.offersState,
            openOfferLink = openOfferLink,
            Modifier.padding(bottom = 32.dp)
        )
        VacancyBlock(
            vacanciesState = uiState.vacanciesState,
            numberOfMoreVacancies = uiState.numberOfMoreVacancies,
            onRetryClick = onRetryClick,
            onFavoriteClick = onFavoriteClick,
            onMoreVacanciesClick = onMoreVacanciesClick
        )
    }
}

@Composable
internal fun OfferBlock(
    offersState: UiState<List<Offer>>,
    openOfferLink: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Box(modifier) {
        when(offersState){
            is UiState.Loading -> ShimmerOfferList()
            is UiState.Failure -> {}
            is UiState.Success -> OfferList(offersState.data, openOfferLink)
        }
    }
}

@Composable
internal fun VacancyBlock(
    vacanciesState: UiState<List<ShortVacancy>>,
    numberOfMoreVacancies: Int,
    onRetryClick: () -> Unit,
    onFavoriteClick: (ShortVacancy) -> Unit,
    onMoreVacanciesClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(modifier) {
        Text(
            text = stringResource(R.string.vacancies_for_you),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        when(vacanciesState){
            is UiState.Loading -> ShimmerVacancyList()
            is UiState.Failure -> {
                ErrorVacancyCard(
                    e = vacanciesState.e,
                    onRetryClick = onRetryClick
                )
            }
            is UiState.Success -> VacancyBlockContent(
                vacancies = vacanciesState.data.take(COMPACT_VACANCIES_LIST_SIZE),
                numberOfMoreVacancies = numberOfMoreVacancies,
                onMoreVacanciesClick = onMoreVacanciesClick,
                onFavoriteClick = onFavoriteClick
            )
        }
    }
}

@Composable
internal fun VacancyBlockContent(
    vacancies: List<ShortVacancy>,
    numberOfMoreVacancies: Int,
    onMoreVacanciesClick: () -> Unit,
    onFavoriteClick: (ShortVacancy) -> Unit,
    modifier: Modifier = Modifier
){
    Column(modifier) {
        CompactVacancyList(
            vacancies = vacancies,
            onFavoriteClick = onFavoriteClick
        )
        if (numberOfMoreVacancies > 0){
            BigButton(
                text = stringResource(
                    R.string.vacancies_more,
                    pluralStringResource(
                        plurals.vacancies_number,
                        numberOfMoreVacancies,
                        numberOfMoreVacancies
                    )
                ),
                onClick = onMoreVacanciesClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )
        }
    }
}

@Composable
internal fun CompactVacancyList(
    vacancies: List<ShortVacancy>,
    onFavoriteClick: (ShortVacancy) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        if (vacancies.isNotEmpty()){
            vacancies.forEach{ vacancy ->
                VacancyCard(
                    onVacancyClick = {},
                    vacancy = vacancy,
                    onFavoriteClick = onFavoriteClick,
                    onApplyClick = {}
                )
            }
        } else {
            EmptyVacanciesCard()
        }

    }
}

@Preview
@Composable
private fun SearchScreenLoadingContentPreview(){
    UiStatePreview(
        uiState = SearchUiState(
            offersState = UiState.Loading,
            vacanciesState = UiState.Loading
        )
    )
}

@Preview
@Composable
private fun SearchScreenErrorContentPreview(){
    UiStatePreview(
        uiState = SearchUiState(
            offersState = UiState.Failure(AppException.UnknownError("")),
            vacanciesState = UiState.Failure(AppException.NetworkError(0,""))
        )
    )
}

@Preview
@Composable
private fun SearchScreenSuccessContentPreview(){
    UiStatePreview(
        uiState = SearchUiState(
            offersState = UiState.Success(offers),
            vacanciesState = UiState.Success(vacancies)
        )
    )
}

@Preview
@Composable
private fun SearchScreenEmptyVacanciesContentPreview(){
    UiStatePreview(
        uiState = SearchUiState(
            offersState = UiState.Success(offers),
            vacanciesState = UiState.Success(emptyList())
        )
    )
}

@Composable
private fun UiStatePreview(uiState: SearchUiState){
    HHTestTaskTheme {
        Surface(Modifier.fillMaxSize()) {
            SearchScreenContent(
                uiState = uiState,
                onRetryClick = {},
                onSearchQueryChange = {},
                onMoreVacanciesClick = {},
                backToMainScreen = {},
                openOfferLink = {},
                onFavoriteClick = {}
            )
        }
    }
}
