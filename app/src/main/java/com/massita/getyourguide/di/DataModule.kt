package com.massita.getyourguide.di

import com.massita.getyourguide.repository.ReviewsDataFactory
import com.massita.getyourguide.repository.ReviewsDataSource
import org.koin.dsl.module

val dataModule = module {
    single { ReviewsDataFactory(get()) }
}