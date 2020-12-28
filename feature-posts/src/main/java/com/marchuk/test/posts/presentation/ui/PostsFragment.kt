package com.marchuk.test.posts.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.marchuk.test.utils.Resource
import com.marchuk.test.core.domain.models.Post
import com.marchuk.test.core.presentation.mvvm.BaseFragment
import com.marchuk.test.posts.PostsComponentProvider
import com.marchuk.test.posts.R
import com.marchuk.test.posts.databinding.FragmentPostsBinding
import com.marchuk.test.posts.presentation.viewmodel.PostsViewModel
import com.marchuk.test.core.presentation.delegates.DelegateAdapterItem
import com.marchuk.test.core.presentation.extensions.gone
import com.marchuk.test.core.presentation.extensions.visible
import javax.inject.Inject

class PostsFragment : BaseFragment<FragmentPostsBinding>(R.layout.fragment_posts) {

    @Inject
    lateinit var viewModel: PostsViewModel
    private lateinit var delegateAdapter: ListDelegationAdapter<List<DelegateAdapterItem>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireContext().applicationContext as PostsComponentProvider)
            .providePostComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstVisibleItem =
                    (binding.recycler.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if (firstVisibleItem > 0) {
                    binding.scrollToTopButton.show()
                } else {
                    binding.scrollToTopButton.hide()
                }
            }
        })
    }

    override fun onStop() {
        super.onStop()
        binding.recycler.clearOnScrollListeners()
    }

    override fun setupUi(savedInstanceState: Bundle?) {
        delegateAdapter = ListDelegationAdapter(PostRecyclerAdapters.postDelegate { post: Post ->
            viewModel.navigateToDetails(post)
        })
        with(binding) {
            errorView.setupAction {
                viewModel.getPosts()
            }
            scrollToTopButton.setOnClickListener {
                recycler.smoothScrollToPosition(0)
            }
            with(recycler) {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = delegateAdapter
            }
        }
    }

    override fun observeViewModel() {
        viewModel.state.observe(this) {
            when (it) {
                is Resource.Success -> {
                    delegateAdapter.apply {
                        items = it.body
                        notifyDataSetChanged()
                    }
                    with(binding) {
                        recycler.visible()
                        errorView.hide()
                    }
                }
                Resource.Loading -> {
                    with(binding) {
                        recycler.gone()
                        errorView.progress()
                        scrollToTopButton.gone()
                    }
                }
                is Resource.Error -> {
                    with(binding) {
                        recycler.gone()
                        errorView.error()
                    }
                }
            }
        }
    }
}