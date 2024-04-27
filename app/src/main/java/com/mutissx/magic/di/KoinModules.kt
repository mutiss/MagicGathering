package com.mutissx.magic.di

import com.mutissx.magic.core.di.coreModule
import com.mutissx.magic.data.di.dataModule
import com.mutissx.magic.domain.di.domainModule
import org.koin.core.module.Module

val koinModules: List<Module> = listOf(
    coreModule,
    dataModule,
    domainModule,
    presentationModule
)
