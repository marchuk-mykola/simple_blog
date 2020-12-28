package com.marchuk.test.core.domain.models

import com.marchuk.test.core.presentation.delegates.DelegateAdapterItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comment(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
): DelegateAdapterItem