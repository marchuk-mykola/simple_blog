package com.marchuk.test.core.domain.models

import com.marchuk.test.core.presentation.delegates.DelegateAdapterItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int,
    var isExpanded: Boolean = false,
) : DelegateAdapterItem