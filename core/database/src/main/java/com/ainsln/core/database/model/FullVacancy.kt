package com.ainsln.core.database.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ainsln.core.database.model.entity.QuestionEntity
import com.ainsln.core.database.model.entity.ScheduleEntity
import com.ainsln.core.database.model.entity.VacancyDetailsEntity
import com.ainsln.core.database.model.entity.BaseVacancyEntity
import com.ainsln.core.database.model.entity.DetailsScheduleCrossRef

data class FullVacancy(
    @Embedded val details: VacancyDetailsEntity,

    @Relation(
        parentColumn = "vacancy_id",
        entityColumn = "id"
    )
    val baseVacancy: BaseVacancyEntity,

    @Relation(
        parentColumn = "vacancy_id",
        entityColumn = "id",
        associateBy = Junction(
            value = DetailsScheduleCrossRef::class,
            parentColumn = "details_id",
            entityColumn = "schedule_id"
        )
    )
    val schedules: List<ScheduleEntity>,

    @Relation(
        parentColumn = "vacancy_id",
        entityColumn = "details_id"
    )
    val questions: List<QuestionEntity>
)
