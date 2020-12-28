package com.marchuk.test.posts.presentation.ui

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.marchuk.test.core.databinding.RecyclerPostBinding
import com.marchuk.test.core.domain.models.Post
import com.marchuk.test.core.presentation.delegates.DelegateAdapterItem

object PostRecyclerAdapters {

    fun postDelegate(onPostClick: (Post) -> Unit ) =
        adapterDelegateViewBinding<Post, DelegateAdapterItem, RecyclerPostBinding>(
            { layoutInflater, root -> RecyclerPostBinding.inflate(layoutInflater, root, false) }
        ) {

            binding.postView.setListener {
                onPostClick(item)
            }

            bind {
                binding.postView.bindView(item, layoutPosition)
            }
        }

}
