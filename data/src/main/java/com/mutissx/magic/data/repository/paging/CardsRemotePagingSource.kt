package com.mutissx.magic.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mutissx.magic.core.api.ErrorParser
import com.mutissx.magic.data.remote.api.CardsApi
import com.mutissx.magic.domain.model.CardsDomain

class CardsRemotePagingSource(private val cardsApi: CardsApi, private val errorParser: ErrorParser): PagingSource<Int, CardsDomain>() {

    override fun getRefreshKey(state: PagingState<Int, CardsDomain>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CardsDomain> {
        return try {
            val currentPage = params.key ?: 1
            val response  = cardsApi.getCards(page = currentPage)
            val endOfPaginationReached = response.cards.isEmpty()
            LoadResult.Page(
                data = response.cards.map { it.toCardsDomain() },
                prevKey = if(currentPage == 1) null else currentPage - 1,
                nextKey = if(endOfPaginationReached) null else currentPage + 1
            )
        }catch(e: Exception){
            LoadResult.Error(errorParser.parseError(e))
        }
    }
}
