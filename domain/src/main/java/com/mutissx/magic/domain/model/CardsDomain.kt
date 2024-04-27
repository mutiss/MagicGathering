package com.mutissx.magic.domain.model

data class CardsDomain(
    val id: String,
    val multiverseId: Int?,
    val name: String,
    val text: String?,
    val type: String?,
    val artist: String?,
    val image: String?
)
