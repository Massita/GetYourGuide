package com.massita.getyourguide.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.massita.getyourguide.R
import com.massita.getyourguide.model.Author
import com.massita.getyourguide.model.Review
import com.massita.getyourguide.util.setTextOrGone
import kotlinx.android.synthetic.main.fragment_review_detail.*
import java.text.DateFormat

class ReviewDetailFragment : Fragment() {
    private val args: ReviewDetailFragmentArgs by navArgs()
    private lateinit var review: Review

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        review = args.review

        setupViews()
    }

    private fun setupViews() {
        reviewTitle.setTextOrGone(review.title)
        reviewComment.setTextOrGone(review.message)
        ratingBar.rating = review.rating.toFloat()
        reviewDate.setTextOrGone(DateFormat.getDateInstance(DateFormat.LONG).format(review.created))
        reviewEnjoyment.setTextOrGone(review.enjoyment)
        travelerType.setTextOrGone(review.travelerType)
        setAuthorDetails(review.author)
    }

    private fun setAuthorDetails(author: Author?) {
        author?.let {
            authorDetails.text = getString(R.string.author_detail, it.fullName, it.country)
        }
    }
}
