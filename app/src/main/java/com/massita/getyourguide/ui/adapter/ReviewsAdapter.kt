package com.massita.getyourguide.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.massita.getyourguide.R
import com.massita.getyourguide.model.Review
import kotlinx.android.synthetic.main.review_item.view.*

class ReviewsAdapter : PagedListAdapter<Review, ReviewsAdapter.ViewHolder> (diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Review>() {
            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = getItem(position)
        review?.let {
            holder.bind(it)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(review: Review) {
            itemView.reviewTitle.text = review.title
            itemView.reviewComment.text = review.message
            itemView.ratingBar.rating = review.rating.toFloat()
        }

    }
}