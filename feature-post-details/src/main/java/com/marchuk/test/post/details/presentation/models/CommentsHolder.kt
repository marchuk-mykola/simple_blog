package com.marchuk.test.post.details.presentation.models

import com.marchuk.test.core.domain.models.Comment
import com.marchuk.test.core.presentation.delegates.DelegateAdapterItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommentsHolder(val list: List<Comment>) : DelegateAdapterItem