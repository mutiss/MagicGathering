package com.mutissx.magic.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mutissx.magic.core.api.ErrorParser
import com.mutissx.magic.data.remote.api.CardsApi
import com.mutissx.magic.data.repository.paging.CardsRemotePagingSource
import com.mutissx.magic.data.util.Constants
import com.mutissx.magic.domain.model.CardsDomain
import com.mutissx.magic.domain.repository.CardsRepository
import kotlinx.coroutines.flow.Flow

class CardsRepositoryImpl(private val cardsApi: CardsApi, private val errorParser: ErrorParser) : CardsRepository {
    override suspend fun getCardsList(): Flow<PagingData<CardsDomain>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.PAGE_SIZE_DEFAULT),
            pagingSourceFactory = { CardsRemotePagingSource(cardsApi = cardsApi, errorParser = errorParser) }
        ).flow
    }

    override suspend fun getCardDetail(idCard: String): CardsDomain {
        return cardsApi.getCardDetail(idCard).card.toCardsDomain()
    }
}
