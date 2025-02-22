package com.ainsln.feature.search

import com.ainsln.core.model.Offer
import com.ainsln.core.model.ShortVacancy
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object PreviewData {

    val offers = listOf(
        Offer(
            id = "near_vacancies",
            title = "Вакансии рядом с вами",
            link = "https://hh.ru/",
            selectedText = null
        ),
        Offer(
            id = "level_up_resume",
            title = "Поднять резюме в поиске",
            link = "https://hh.ru/mentors?from=footer_new&hhtmFromLabel=footer_new&hhtmFrom=main&purposeId=1",
            selectedText = "Поднять"
        ),
        Offer(
            id = "temporary_job",
            title = "Временная работа или подработка",
            link = "https://hh.ru/",
            selectedText = null
        ),
        Offer(
            id = null,
            title = "Полезные статьи и советы",
            link = "https://hh.ru/articles?hhtmFrom=main",
            selectedText = null
        )
    )

    val vacancies = listOf(
        ShortVacancy(
            id = "cbf0c984-7c6c-4ada-82da-e29dc698bb50",
            isFavorite = false,
            lookingNumber = 2,
            title = "UI/UX дизайнер",
            salaryShort = null,
            town = "Минск",
            company = "Мобирикс",
            experiencePreview = "Опыт от 1 до 3 лет",
            publishedDate =parseDate("2024-02-20")
        ),
        ShortVacancy(
            id = "54a876a5-2205-48ba-9498-cfecff4baa6e",
            isFavorite = false,
            lookingNumber = 17,
            title = "UI/UX-дизайнер / Web-дизайнер / Дизайнер интерфейсов",
            salaryShort = "20 000 до 50 000 ₽",
            town = "Казань",
            company = "Шафигуллин Шакир",
            experiencePreview = "Опыт от 1 до 3 лет",
            publishedDate = parseDate("2024-03-06")
        ),
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
            id = "16f88865-ae74-4b7c-9d85-b68334bb97db",
            isFavorite = false,
            lookingNumber = 57,
            title = "Веб-дизайнер",
            salaryShort = "от 60 000 ₽",
            town = "Казань",
            company = "Алабуга. Маркетинг и PR",
            experiencePreview = "Без опыта",
            publishedDate = parseDate("2024-03-02")
        ),
        ShortVacancy(
            id = "26f88856-ae74-4b7c-9d85-b68334bb97db",
            isFavorite = false,
            lookingNumber = 29,
            title = "Ведущий продуктовый дизайнер",
            salaryShort = null,
            town = "Минск",
            company = "ПАО Ростелеком",
            experiencePreview = "Опыт от 3 до 6 лет",
            publishedDate = parseDate("2024-02-19")
        ),
        ShortVacancy(
            id = "15f88865-ae74-4b7c-9d81-b78334bb97db",
            isFavorite = false,
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
