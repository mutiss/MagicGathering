package com.mutissx.magic.presentation.screens

import com.mutissx.magic.presentation.util.Constants

sealed class Screen(val route: String) {
    object CandidateInfoScreen : Screen("candidate_info_screen")
    object CardsListScreen : Screen("cards_list_screen")
    object CardsDetailScreen :
        Screen("cards_detail_screen/{${Constants.CARDS_DETAILS_ARGUMENT_KEY}}") {
        fun passIdCard(idCard: String) = "cards_detail_screen/$idCard"
    }
}
