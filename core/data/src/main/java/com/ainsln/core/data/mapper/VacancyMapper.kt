package com.ainsln.core.data.mapper

import com.ainsln.core.database.model.FullVacancy
import com.ainsln.core.database.model.entity.AddressTuple
import com.ainsln.core.database.model.entity.BaseVacancyEntity
import com.ainsln.core.database.model.entity.QuestionEntity
import com.ainsln.core.database.model.entity.ScheduleEntity
import com.ainsln.core.database.model.entity.VacancyDetailsEntity
import com.ainsln.core.model.ShortVacancy
import com.ainsln.core.network.model.VacancyDTO
import jakarta.inject.Inject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


interface VacancyMapper {
    fun dtoToBaseEntity(dto: VacancyDTO): BaseVacancyEntity
    fun dtoToDomain(dto: VacancyDTO): ShortVacancy
    fun dtoToFullEntity(dto: VacancyDTO): FullVacancy
    fun baseEntityToDomain(baseEntity: BaseVacancyEntity): ShortVacancy
    fun domainToBaseEntity(domain: ShortVacancy): BaseVacancyEntity
}

class VacancyMapperImpl @Inject constructor() : VacancyMapper {

    override fun dtoToBaseEntity(dto: VacancyDTO) = BaseVacancyEntity(
        id = dto.id,
        title = dto.title,
        company = dto.company,
        isFavorite = false,
        lookingNumber = dto.lookingNumber,
        publishedDate = parseDate(dto.publishedDate),
        salaryShort = dto.salary?.short,
        town = dto.address?.town,
        experiencePreview = dto.experience?.previewText
    )

    override fun dtoToDomain(dto: VacancyDTO) = ShortVacancy(
        id = dto.id,
        title = dto.title,
        company = dto.company,
        isFavorite = false,
        lookingNumber = dto.lookingNumber,
        publishedDate = parseDate(dto.publishedDate),
        salaryShort = dto.salary?.short,
        town = dto.address?.town,
        experiencePreview = dto.experience?.previewText
    )

    override fun baseEntityToDomain(baseEntity: BaseVacancyEntity) = ShortVacancy(
        id = baseEntity.id,
        title = baseEntity.title,
        company = baseEntity.company,
        isFavorite = baseEntity.isFavorite,
        lookingNumber = baseEntity.lookingNumber,
        publishedDate = baseEntity.publishedDate,
        salaryShort = baseEntity.salaryShort,
        town = baseEntity.town,
        experiencePreview = baseEntity.experiencePreview
    )

    override fun dtoToFullEntity(dto: VacancyDTO) = FullVacancy(
        baseVacancy = dtoToBaseEntity(dto),
        details = VacancyDetailsEntity(
            vacancyId = dto.id,
            description = dto.description,
            responsibilities = dto.responsibilities,
            appliedNumber = dto.appliedNumber,
            salaryFull = dto.salary?.full,
            experienceText = dto.experience?.text,
            address = dto.address?.let { AddressTuple(it.street, it.house) }
        ),
        schedules = dto.schedules?.map { schedule ->
            ScheduleEntity(0, schedule)
        } ?: emptyList(),
        questions = dto.questions?.map { text ->
            QuestionEntity(0, "", text)
        } ?: emptyList()
    )

    override fun domainToBaseEntity(domain: ShortVacancy) = BaseVacancyEntity(
        id = domain.id,
        title = domain.title,
        company = domain.company,
        isFavorite = domain.isFavorite,
        lookingNumber = domain.lookingNumber,
        publishedDate = domain.publishedDate,
        salaryShort = domain.salaryShort,
        town = domain.town,
        experiencePreview = domain.experiencePreview
    )

    private fun parseDate(dateString: String): Date {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.parse(dateString) ?: Date()
    }
}


//interface VacancyMapper {
//    fun mapDtoToShortDomain(dto: VacancyDTO, isFavorite: Boolean = false): ShortVacancy
//    fun mapDtoToDomain(dto: VacancyDTO): Vacancy
//    fun mapDomainToEntity(domain: Vacancy): FullVacancy
//    fun mapEntityToDomain(entity: FullVacancy): ShortVacancy
//}

//class VacancyMapperImpl @Inject constructor() : VacancyMapper {
//
//    override fun mapDtoToShortDomain(dto: VacancyDTO, isFavorite: Boolean) = ShortVacancy(
//        id = dto.id,
//        isFavorite = isFavorite,
//        lookingNumber = dto.lookingNumber,
//        title = dto.title,
//        salaryShort = dto.salary?.short,
//        town = dto.address?.town,
//        company = dto.company,
//        experiencePreview = dto.experience?.previewText,
//        publishedDate = parseDate(dto.publishedDate)
//    )
//
//    override fun mapDomainToEntity(domain: Vacancy) = FullVacancy(
//        vacancy = BaseVacancyEntity(
//            id = domain.id,
//            title = domain.title,
//            company = domain.company,
//            description = domain.description,
//            responsibilities = domain.responsibilities,
//            lookingNumber = domain.lookingNumber,
//            publishedDate = domain.publishedDate,
//            appliedNumber = domain.appliedNumber,
//            salary = domain.salary?.let { SalaryTuple(it.full, it.short) },
//            experience = domain.experience?.let { ExperienceTuple(it.previewText, it.text) },
//            address = domain.address?.let { AddressTuple(it.town, it.street, it.house) }
//        ),
//        schedules = domain.schedules?.map { schedule ->
//            ScheduleEntity(0, schedule)
//        } ?: emptyList(),
//        questions = domain.questions?.map { text ->
//            QuestionEntity(0, "", text)
//        } ?: emptyList()
//    )
//
//    override fun mapDtoToDomain(dto: VacancyDTO) = Vacancy(
//        id = dto.id,
//        lookingNumber = dto.lookingNumber,
//        title = dto.title,
//        address = dto.address?.let { Address(it.town, it.street, it.house) },
//        company = dto.company,
//        experience = dto.experience?.let { Experience(it.previewText, it.text) },
//        publishedDate = parseDate(dto.publishedDate),
//        isFavorite = dto.isFavorite ?: false,
//        salary = dto.salary?.let { Salary(it.full, it.short) },
//        schedules = dto.schedules,
//        appliedNumber = dto.appliedNumber,
//        description = dto.description,
//        questions = dto.questions,
//        responsibilities = dto.responsibilities
//    )
//
//    override fun mapEntityToDomain(entity: FullVacancy) = ShortVacancy(
//        id = entity.vacancy.id,
//        isFavorite = true,
//        lookingNumber = entity.vacancy.lookingNumber,
//        title = entity.vacancy.title,
//        salaryShort = entity.vacancy.salary?.short,
//        town = entity.vacancy.address?.town,
//        company = entity.vacancy.company,
//        experiencePreview = entity.vacancy.experience?.previewText,
//        publishedDate = entity.vacancy.publishedDate
//    )
//
//    private fun parseDate(dateString: String): Date {
//        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//        return format.parse(dateString) ?: Date()
//    }
//
//}
