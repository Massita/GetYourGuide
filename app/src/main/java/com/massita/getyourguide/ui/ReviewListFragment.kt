package com.massita.getyourguide.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.massita.getyourguide.R
import com.massita.getyourguide.repository.NetworkState
import com.massita.getyourguide.repository.Status
import com.massita.getyourguide.ui.adapter.ReviewsAdapter
import com.massita.getyourguide.viewmodel.ReviewListViewModel
import kotlinx.android.synthetic.main.fragment_review_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class ReviewListFragment : Fragment() {

    val reviewListViewModel: ReviewListViewModel by viewModel()

    private lateinit var reviewsAdapter: ReviewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        reviewsAdapter = ReviewsAdapter()

        reviewsRecyclerView.apply {
            val mLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val dividerItemDecoration = DividerItemDecoration(context, mLayoutManager.orientation)
            addItemDecoration(dividerItemDecoration)
            layoutManager = mLayoutManager
            setHasFixedSize(true)
            adapter = reviewsAdapter
        }

        reviewListViewModel.getReviews().pagedList.observe(viewLifecycleOwner, Observer { reviewsAdapter.submitList(it) })
        reviewListViewModel.getReviews().networkState.observe(viewLifecycleOwner, Observer { handleNetworkChange(it) })
    }

    private fun handleNetworkChange(networkState: NetworkState) {
        if(networkState.status == Status.FAILED) {
            networkState.msg?.let {
                showSnackbar(it)
            }
        }
    }

    private fun showSnackbar(message: String){
        view?.let {
            Snackbar
                .make(it, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, { reviewListViewModel.getReviews().retry() })
                .show()
        }
    }
}
