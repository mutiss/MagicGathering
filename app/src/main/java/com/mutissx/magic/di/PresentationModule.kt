package com.mutissx.magic.di

import com.mutissx.magic.presentation.screens.cards_detail.CardsDetailViewModel
import com.mutissx.magic.presentation.screens.cards_list.CardsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { CardsListViewModel(get()) }
    viewModel { CardsDetailViewModel(get(), get()) }
}
