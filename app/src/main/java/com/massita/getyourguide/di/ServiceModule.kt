package com.massita.getyourguide.di

import com.massita.getyourguide.api.ApiClient
import org.koin.dsl.module

val serviceModule = module {
    single { ApiClient.create() }
}