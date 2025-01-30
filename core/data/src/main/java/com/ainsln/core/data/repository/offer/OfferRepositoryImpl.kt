package com.ainsln.core.data.repository.offer

import com.ainsln.core.common.di.IODispatcher
import com.ainsln.core.common.result.DataResult
import com.ainsln.core.common.result.asFlowResult
import com.ainsln.core.common.result.exception.ExceptionHandler
import com.ainsln.core.data.mapper.OfferMapper
import com.ainsln.core.model.Offer
import com.ainsln.core.network.NetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class OfferRepositoryImpl @Inject constructor(
    @IODispatcher private val dispatcher: CoroutineDispatcher,
    private val networkDataSource: NetworkDataSource,
    private val exceptionHandler: ExceptionHandler,
    private val offerMapper: OfferMapper
) : OfferRepository {

    override fun getOffers(): Flow<DataResult<List<Offer>>> = flow {
        networkDataSource.getOffers()
            .onSuccess { data ->
                emit(data.map { offerMapper.dtoToDomain(it) })
            }.onFailure { e -> throw e }
    }.asFlowResult(dispatcher, exceptionHandler::handle)

}
