package com.mutissx.magic.data.remote.api

import com.mutissx.magic.data.remote.dto.CardResponse
import com.mutissx.magic.data.remote.dto.CardsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CardsApi {
    @GET("v1/cards")
    suspend fun getCards(
        @Query("page") page: Int
    ): CardsResponse

    @GET("v1/cards/{idCard}")
    suspend fun getCardDetail(
        @Path("idCard") idCard: String
    ): CardResponse
}
