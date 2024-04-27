package com.mutissx.magic.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.mutissx.magic.domain.model.CardsDomain

data class CardsResponse(
    @SerializedName("cards") val cards: List<CardsDto>
)

data class CardResponse(
    @SerializedName("card") val card: CardsDto
)

data class CardsDto(
    val id: String,
    @SerializedName("multiverseid")
    val multiverseId: Int?,
    val name: String,
    val text: String?,
    val type: String?,
    val artist: String?,
    @SerializedName("imageUrl")
    val image: String? = null
) {
    fun toCardsDomain(): CardsDomain {
        return CardsDomain(
            id = id,
            multiverseId = multiverseId,
            name = name,
            text = text,
            type = type,
            artist = artist,
            image = image
        )
    }
}
