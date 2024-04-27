package com.mutissx.magic.core.di

import com.google.gson.Gson
import com.mutissx.magic.core.api.ErrorParser
import com.mutissx.magic.core.testing.DefaultDispatcherProvider
import com.mutissx.magic.core.testing.DispatcherProvider
import org.koin.dsl.module


val coreModule = module {
  single { ErrorParser(get()) }
  single { Gson() }
  single<DispatcherProvider> { DefaultDispatcherProvider() }
}
