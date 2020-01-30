package com.massita.getyourguide.model

data class ReviewsResponse(
    val reviews: List<Review>,
    val totalCount: Int,
    val averageRating: Float,
    val pagination: Pagination
)