package com.mutissx.magic.presentation.screens.cards_detail.viewstate

import com.mutissx.magic.domain.model.CardsDomain

sealed class CardsDetailState {
    object CardsDetailLoadingState : CardsDetailState()
    data class CardsDetailSuccessState(val cardDetail: CardsDomain?) : CardsDetailState()
    data class CardsDetailErrorState(val messageError: String) : CardsDetailState()
}
