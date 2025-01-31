package com.ainsln.core.ui.component.vacancy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ainsln.core.model.ShortVacancy

@Composable
fun VacancyLazyList(
    vacancies: List<ShortVacancy>,
    header: @Composable () -> Unit,
    onFavoriteClick: (ShortVacancy) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(bottom = 16.dp)
){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = contentPadding,
        modifier = modifier
    )  {
        item { header() }

        items(vacancies) { vacancy ->
            VacancyCard(
                onVacancyClick = {},
                vacancy = vacancy,
                onFavoriteClick = onFavoriteClick,
                onApplyClick = {}
            )
        }
    }
}

@Composable
fun ShimmerVacancyList(
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        repeat(3) { ShimmerVacancyCard() }
    }
}
