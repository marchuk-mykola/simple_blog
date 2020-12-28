package com.marchuk.test.core.presentation.delegates

import kotlinx.parcelize.Parcelize

sealed class RecyclerDelegate {

    @Parcelize
    object Loading : DelegateAdapterItem, RecyclerDelegate()

    @Parcelize
    object Error : DelegateAdapterItem, RecyclerDelegate()

}