package com.ainsln.feature.favorite

import com.ainsln.core.model.ShortVacancy
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object PreviewData {

    val vacancies = listOf(
        ShortVacancy(
            id = "75c84407-52e1-4cce-a73a-ff2d3ac031b3",
            isFavorite = true,
            lookingNumber = null,
            title = "UX/UI Designer",
            salaryShort = null,
            town = "Казань",
            company = "Aston",
            experiencePreview = "Опыт от 3 до 6 лет",
            publishedDate = parseDate("2024-02-28")
        ),
        ShortVacancy(
            id = "15f88865-ae74-4b7c-9d81-b78334bb97db",
            isFavorite = true,
            lookingNumber = 1,
            title = "Product Designer",
            salaryShort = null,
            town = "Минск",
            company = "TravelLine",
            experiencePreview = "Опыт от 3 до 6 лет",
            publishedDate = parseDate("2024-02-02")
        )
    )

    private fun parseDate(dateString: String): Date {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.parse(dateString) ?: Date()
    }
}
