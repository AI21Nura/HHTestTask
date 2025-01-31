package com.ainsln.core.ui.component.vacancy

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ainsln.core.common.result.exception.AppException
import com.ainsln.core.designsystem.component.FavoriteToggleButton
import com.ainsln.core.designsystem.component.MediumButton
import com.ainsln.core.designsystem.component.SafeText
import com.ainsln.core.designsystem.component.SecondaryText
import com.ainsln.core.designsystem.component.SelectedText
import com.ainsln.core.designsystem.icon.AppIcons
import com.ainsln.core.designsystem.theme.HHTestTaskTheme
import com.ainsln.core.model.ShortVacancy
import com.ainsln.core.ui.R
import com.ainsln.core.ui.component.ErrorBlock
import com.ainsln.core.ui.component.PlaceholderBlock
import com.ainsln.core.ui.component.TextShimmerEffect
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun VacancyCard(
    vacancy: ShortVacancy,
    onVacancyClick: (String) -> Unit,
    onFavoriteClick: (ShortVacancy) -> Unit,
    onApplyClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    VacancyCard(
        onVacancyClick = { onVacancyClick(vacancy.id) },
        modifier = modifier,
        content = {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            VacancyInfo(
                vacancy = vacancy,
                modifier = Modifier.weight(1f)
            )
            FavoriteToggleButton(
                checked = vacancy.isFavorite,
                onCheckedChange = { onFavoriteClick(vacancy) }
            )
        }
        MediumButton(
            text = stringResource(R.string.apply_button),
            onClick = { onApplyClick(vacancy.id) },
            modifier = Modifier.fillMaxWidth()
        )
    })
}

@Composable
fun ShimmerVacancyCard(modifier: Modifier = Modifier) {
    VacancyCard(
        onVacancyClick = {},
        modifier = modifier,
        content = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                TextShimmerEffect(height = 20.dp)
                TextShimmerEffect(widthFraction = 0.8f, height = 20.dp)
            }
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                TextShimmerEffect(widthFraction = 0.8f, height = 12.dp)
                TextShimmerEffect(widthFraction = 0.6f, height = 12.dp)
                TextShimmerEffect(widthFraction = 0.6f, height = 12.dp)
            }
            TextShimmerEffect(height = 40.dp)
        })
}

@Composable
fun ErrorVacancyCard(
    e: AppException,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
){
    VacancyCard(
        onVacancyClick = {},
        modifier = modifier,
        content = {
            ErrorBlock(
                e = e,
                onRetryClick = onRetryClick
            )
        }
    )
}

@Composable
fun EmptyVacanciesCard(
    modifier: Modifier = Modifier
){
    VacancyCard(
        onVacancyClick = {},
        modifier = modifier,
        content = {
            PlaceholderBlock(
                text = stringResource(R.string.empty_vacancies_placeholder),
                icon = AppIcons.Empty,
                compact = true,
                modifier = Modifier.padding(vertical = 32.dp)
            )
        }
    )
}

@Composable
private fun VacancyCard(
    onVacancyClick: () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable { onVacancyClick() }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            content()
        }
    }
}

@Composable
private fun VacancyInfo(
    vacancy: ShortVacancy,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
    ) {
        vacancy.lookingNumber?.let {
            SelectedText(
                pluralStringResource(R.plurals.vacancy_viewers, it, it)
            )
        }

        Text(
            text = vacancy.title,
            style = MaterialTheme.typography.titleSmall
        )

        SafeText(
            text = vacancy.salaryShort,
            style = MaterialTheme.typography.titleMedium
        )

        CompanyBlock(vacancy.town, vacancy.company)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = AppIcons.Experience,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline
            )
            SafeText(vacancy.experiencePreview)
        }

        SecondaryText(
            text = stringResource(
                R.string.published_date,
                publicationDate(vacancy.publishedDate)
            )
        )
    }
}

private fun publicationDate(date: Date): String {
    return SimpleDateFormat("d MMMM", Locale("ru")).format(date)
}

@Composable
private fun CompanyBlock(
    city: String?,
    companyName: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        SafeText(city)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = companyName)
            Icon(
                imageVector = AppIcons.Verification,
                contentDescription = stringResource(R.string.сompany_verified),
                tint = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun VacancyCardPreview() {
    HHTestTaskTheme {
        Surface {
            VacancyCard(
                onVacancyClick = {},
                vacancy = ShortVacancy(
                    id = "",
                    title = "UI/UX дизайнер",
                    salaryShort = "20 000 до 50 000 ₽",
                    town = "Минкс",
                    company = "Мобирикс",
                    experiencePreview = "Опыт от 1 до 3 лет",
                    publishedDate = Date(System.currentTimeMillis()),
                    isFavorite = false,
                    lookingNumber = 4
                ),
                onFavoriteClick = {},
                onApplyClick = {},
            )
        }
    }
}
