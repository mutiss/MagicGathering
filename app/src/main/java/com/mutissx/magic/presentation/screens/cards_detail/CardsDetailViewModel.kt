package com.mutissx.magic.presentation.screens.cards_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutissx.magic.core.domain.common.Resource
import com.mutissx.magic.domain.usecase.GetCardDetailUseCase
import com.mutissx.magic.presentation.screens.cards_detail.viewstate.CardsDetailState
import com.mutissx.magic.presentation.util.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CardsDetailViewModel(
    private val getCardDetailUseCase: GetCardDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow<CardsDetailState>(CardsDetailState.CardsDetailLoadingState)
    val state: StateFlow<CardsDetailState> = _state

    init {
        getCardsDetail()
    }

    fun getCardsDetail() {
        savedStateHandle.get<String>(key = Constants.CARDS_DETAILS_ARGUMENT_KEY)?.let {
            val idCard = it
            viewModelScope.launch {
                getCardDetailUseCase.invokeWithParams(idCard = idCard).collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = CardsDetailState.CardsDetailSuccessState(
                                cardDetail = result.data
                            )
                        }
                        is Resource.Error -> {
                            _state.value = CardsDetailState.CardsDetailErrorState(
                                messageError = result.message ?: "An unexpected error occurred!"
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = CardsDetailState.CardsDetailLoadingState
                        }
                    }
                }
            }
        }
    }
}
