package com.massita.getyourguide.di

import com.massita.getyourguide.repository.ReviewsDataFactory
import org.koin.dsl.module
import java.util.concurrent.Executors

val dataModule = module {
    single { ReviewsDataFactory(get(), Executors.newFixedThreadPool(5)) }
}