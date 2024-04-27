package com.mutissx.magic.presentation.screens.cards_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mutissx.magic.domain.model.CardsDomain
import com.mutissx.magic.domain.usecase.GetCardsListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class CardsListViewModel (
    private val getCardsListUseCase: GetCardsListUseCase
) : ViewModel() {

    private val _cardsListState: MutableStateFlow<PagingData<CardsDomain>> =
        MutableStateFlow(value = PagingData.empty())
    val cardsListState: StateFlow<PagingData<CardsDomain>>
        get() = _cardsListState

    init {
        getCardsList()
    }

    private fun getCardsList(){
        viewModelScope.launch {
            getCardsListUseCase()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collectLatest {
                    _cardsListState.value = it
                }
        }
    }
}
