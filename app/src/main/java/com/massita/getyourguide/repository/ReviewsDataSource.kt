package com.massita.getyourguide.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.massita.getyourguide.api.ApiClient
import com.massita.getyourguide.model.Review
import com.massita.getyourguide.model.ReviewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class ReviewsDataSource(
    private val apiClient: ApiClient,
    private val retryExecutor: Executor
) : ItemKeyedDataSource<Int, Review>() {

    private var totalLoaded = 0

    val networkState = MutableLiveData<NetworkState>()
    var retry: (() -> Any)? = null

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let { retry ->
            retryExecutor.execute { retry() }
        }
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Review>
    ) {
        networkState.postValue(NetworkState.LOADING)
        apiClient.getReviews(
            limit = params.requestedLoadSize,
            offset = 0
        ).enqueue(object : Callback<ReviewsResponse> {
            override fun onFailure(call: Call<ReviewsResponse>, t: Throwable) {
                retry = { loadInitial(params, callback) }
                networkState.postValue(NetworkState.error(t.message ?: "Unknown error"))
            }

            override fun onResponse(
                call: Call<ReviewsResponse>,
                response: Response<ReviewsResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.reviews
                    val items = data?.map { it } ?: emptyList()
                    retry = null
                    totalLoaded = response.body()?.pagination?.limit!! + response.body()?.pagination?.offset!!
                    callback.onResult(items)
                    networkState.postValue(NetworkState.LOADED)
                } else {
                    retry = { loadInitial(params, callback) }
                    networkState.postValue(
                        NetworkState.error("error code: ${response.code()}"))
                }
            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Review>) {
        networkState.postValue(NetworkState.LOADING)
        apiClient.getReviews(
            limit = params.requestedLoadSize,
            offset = params.key
        ).enqueue(object : Callback<ReviewsResponse> {
            override fun onFailure(call: Call<ReviewsResponse>, t: Throwable) {
                retry = { loadAfter(params, callback) }
                networkState.postValue(NetworkState.error(t.message ?: "Unknown error"))
            }

            override fun onResponse(
                call: Call<ReviewsResponse>,
                response: Response<ReviewsResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.reviews
                    val items = data?.map { it } ?: emptyList()
                    retry = null
                    totalLoaded = response.body()?.pagination?.limit!! + response.body()?.pagination?.offset!!
                    callback.onResult(items)
                    networkState.postValue(NetworkState.LOADED)
                } else {
                    retry = { loadAfter(params, callback) }
                    networkState.postValue(
                        NetworkState.error("error code: ${response.code()}"))
                }
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Review>) {
        // Ignored.
    }

    override fun getKey(item: Review): Int {
        return totalLoaded
    }

}