package com.ainsln.core.data.repository.offer

import com.ainsln.core.common.result.DataResult
import com.ainsln.core.model.Offer
import kotlinx.coroutines.flow.Flow

interface OfferRepository {
    fun getOffers(): Flow<DataResult<List<Offer>>>
}
