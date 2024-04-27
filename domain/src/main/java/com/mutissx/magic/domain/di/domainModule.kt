package com.mutissx.magic.domain.di

import com.mutissx.magic.domain.usecase.GetCardDetailUseCase
import com.mutissx.magic.domain.usecase.GetCardsListUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetCardsListUseCase(get()) }
    single { GetCardDetailUseCase(get(), get()) }
}
