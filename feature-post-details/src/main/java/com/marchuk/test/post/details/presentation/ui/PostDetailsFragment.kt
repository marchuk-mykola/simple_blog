package com.marchuk.test.post.details.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.marchuk.test.core.domain.models.Post
import com.marchuk.test.core.presentation.delegates.BaseDelegates
import com.marchuk.test.core.presentation.delegates.DelegateAdapterItem
import com.marchuk.test.core.presentation.mvvm.BaseFragment
import com.marchuk.test.post.details.R
import com.marchuk.test.post.details.databinding.FragmentPostDetailsBinding
import com.marchuk.test.post.details.di.PostDetailsComponentProvider
import com.marchuk.test.post.details.presentation.viewmodel.PostDetailsViewModel
import javax.inject.Inject


class PostDetailsFragment : BaseFragment<FragmentPostDetailsBinding>(R.layout.fragment_post_details) {

    companion object {
        private const val POST = "post id"

        fun createBundle(post: Post): Bundle {
            return Bundle().apply {
                putParcelable(POST, post)
            }
        }
    }

    @Inject
    lateinit var viewModel: PostDetailsViewModel
    private lateinit var delegateAdapter: ListDelegationAdapter<List<DelegateAdapterItem>>
    private val post: Post by lazy { requireArguments().getParcelable(POST)!! }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPostDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireContext().applicationContext as PostDetailsComponentProvider)
            .providePostDetailsComponent().inject(this)
        viewModel.getComments(post)
    }

    override fun setupUi(savedInstanceState: Bundle?) {
        delegateAdapter = ListDelegationAdapter(
            PostDetailsDelegateAdapters.postDelegate(),
            PostDetailsDelegateAdapters.commentsDelegate(),
            BaseDelegates.loadingDelegateAdapter(),
            BaseDelegates.errorDelegateAdapter {
                viewModel.getComments(post)
            }
        )
        with(binding) {
            with(recycler) {
                adapter = delegateAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    override fun observeViewModel() {
        viewModel.state.observe(this) {
            delegateAdapter.apply {
                // Update on loading
                if (items.isNullOrEmpty()) {
                    items = it
                    notifyDataSetChanged()
                } else {
                    // update on success or error, post is not changed
                    items = it
                    notifyItemChanged(1)
                }
            }
        }
    }

}