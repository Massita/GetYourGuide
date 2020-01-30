package com.massita.getyourguide.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.massita.getyourguide.api.ApiClient
import com.massita.getyourguide.model.Review

class ReviewsDataFactory(private val apiClient: ApiClient) : DataSource.Factory<Int, Review>() {
    var reviewsLiveData = MutableLiveData<ReviewsDataSource>()
    private lateinit var reviewsDataSource: ReviewsDataSource

    override fun create(): DataSource<Int, Review> {
        reviewsDataSource = ReviewsDataSource(apiClient)
        reviewsLiveData.postValue(reviewsDataSource)
        return reviewsDataSource
    }

}