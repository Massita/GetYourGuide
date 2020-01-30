package com.massita.getyourguide.di

import com.massita.getyourguide.viewmodel.ReviewListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ReviewListViewModel() }
}