package com.ainsln.core.data.mapper

import com.ainsln.core.model.Offer
import com.ainsln.core.network.model.OfferDTO
import javax.inject.Inject

interface OfferMapper {
    fun dtoToDomain(dto: OfferDTO): Offer
}

class OfferMapperImpl @Inject constructor() : OfferMapper {
    override fun dtoToDomain(dto: OfferDTO) = Offer(
        id = dto.id,
        title = dto.title,
        link = dto.link,
        selectedText = dto.button?.text
    )
}
