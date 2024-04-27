package com.mutissx.magic.domain.repository

import androidx.paging.PagingData
import com.mutissx.magic.domain.model.CardsDomain
import kotlinx.coroutines.flow.Flow

interface CardsRepository {
    suspend fun getCardsList(): Flow<PagingData<CardsDomain>>
    suspend fun getCardDetail(idCard: String): CardsDomain
}
