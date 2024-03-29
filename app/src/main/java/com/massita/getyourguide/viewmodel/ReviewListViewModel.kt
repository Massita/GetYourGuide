package com.massita.getyourguide.viewmodel

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.massita.getyourguide.model.Review
import com.massita.getyourguide.repository.Listing
import com.massita.getyourguide.repository.ReviewsDataFactory
import org.koin.core.KoinComponent
import org.koin.core.inject

class ReviewListViewModel : ViewModel(), KoinComponent {

    private var reviewList: LiveData<PagedList<Review>>
    private val reviewsDataFactory: ReviewsDataFactory by inject()

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(5)
            .setInitialLoadSizeHint(5)
            .setEnablePlaceholders(true)
            .build()

        reviewList = LivePagedListBuilder<Int, Review>(reviewsDataFactory, config).build()
    }

    @MainThread
    fun getReviews(): Listing<Review> {
        return Listing(
            pagedList = reviewList,
            networkState = switchMap(reviewsDataFactory.reviewsLiveData) { it.networkState },
            retry = { reviewsDataFactory.reviewsLiveData.value?.retryAllFailed() }
        )
    }
}