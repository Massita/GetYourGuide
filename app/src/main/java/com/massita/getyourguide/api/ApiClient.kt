package com.massita.getyourguide.api

import com.massita.getyourguide.model.ReviewsResponse
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("/activities/23776/reviews")
    fun getReviews(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int) : Call<ReviewsResponse>

    companion object{
        private const val BASE_URL = "https://travelers-api.getyourguide.com"

        fun create(): ApiClient = create(HttpUrl.parse(BASE_URL)!!)

        private fun create(baseUrl: HttpUrl): ApiClient {
            // Creating the OkHttp Client
            val client = OkHttpClient.Builder()
                .addInterceptor(Interceptor { chain ->
                    val request: Request =
                        chain.request().newBuilder().addHeader("User-Agent", "GetYourGuide").build()
                    chain.proceed(request)
                })
                .build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiClient::class.java)
        }

    }
}