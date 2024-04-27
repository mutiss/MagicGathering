package com.mutissx.magic.domain.usecase

import androidx.paging.PagingData
import com.mutissx.magic.domain.model.CardsDomain
import com.mutissx.magic.domain.repository.CardsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetCardsListUseCase(
    private val repository: CardsRepository
) {
    suspend operator fun invoke(): Flow<PagingData<CardsDomain>> {
        return withContext(Dispatchers.IO) { repository.getCardsList() }
    }
}
