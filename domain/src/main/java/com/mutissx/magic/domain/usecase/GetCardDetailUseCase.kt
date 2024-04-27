package com.mutissx.magic.domain.usecase

import com.mutissx.magic.core.api.ErrorParser
import com.mutissx.magic.core.domain.common.BaseUseCase
import com.mutissx.magic.core.domain.common.Resource
import com.mutissx.magic.domain.model.CardsDomain
import com.mutissx.magic.domain.repository.CardsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCardDetailUseCase(
    private val cardsRepository: CardsRepository,
    private val errorParser: ErrorParser
) : BaseUseCase() {
    fun invokeWithParams(idCard: String): Flow<Resource<CardsDomain>> = flow {
        emit(Resource.Loading())
        emit(invoke(errorParser) { cardsRepository.getCardDetail(idCard) })
    }
}
