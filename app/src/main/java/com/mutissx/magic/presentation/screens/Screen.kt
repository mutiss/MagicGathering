package com.mutissx.magic.presentation.screens

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object CandidateInfoScreen : Screen
    @Serializable
    data object CardsListScreen : Screen

    @Serializable
    data class CardsDetailScreen(val idCard: String) : Screen
}
