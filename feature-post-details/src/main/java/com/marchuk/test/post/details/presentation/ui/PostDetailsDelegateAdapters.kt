package com.marchuk.test.post.details.presentation.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.marchuk.test.core.presentation.extensions.addDivider16dp
import com.marchuk.test.core.databinding.RecyclerPostBinding
import com.marchuk.test.core.domain.models.Comment
import com.marchuk.test.core.domain.models.Post
import com.marchuk.test.post.details.databinding.RecyclerCommentBinding
import com.marchuk.test.post.details.databinding.RecyclerCommentsBinding
import com.marchuk.test.core.presentation.delegates.DelegateAdapterItem
import com.marchuk.test.post.details.presentation.models.CommentsHolder

object PostDetailsDelegateAdapters {

    internal fun postDelegate() =
        adapterDelegateViewBinding<Post, DelegateAdapterItem, RecyclerPostBinding>(
            { layoutInflater, root -> RecyclerPostBinding.inflate(layoutInflater, root, false) }
        ) {

            bind {
                binding.postView.bindView(item)
            }
        }

    private fun commentDelegate() =
        adapterDelegateViewBinding<Comment, DelegateAdapterItem, RecyclerCommentBinding>(
            { layoutInflater, root -> RecyclerCommentBinding.inflate(layoutInflater, root, false) }
        ) {

            bind {
                with(binding) {
                    comment.text = item.body
                    email.text = item.email
                    name.text = item.name
                }
            }
        }

    internal fun commentsDelegate() =
        adapterDelegateViewBinding<CommentsHolder, DelegateAdapterItem, RecyclerCommentsBinding>(
            { layoutInflater, root -> RecyclerCommentsBinding.inflate(layoutInflater, root, false) }
        ) {

            val delegateAdapter = ListDelegationAdapter(commentDelegate())

            bind {
                with(binding) {
                    commentsCount.text = "Comments (${item.list.size})"
                    with(recycler) {
                        adapter = delegateAdapter
                        layoutManager = LinearLayoutManager(context)
                        addDivider16dp()
                    }
                }

                delegateAdapter.items = item.list
                delegateAdapter.notifyDataSetChanged()
            }
        }


}
