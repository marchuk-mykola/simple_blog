package com.marchuk.test.core.presentation.delegates

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.marchuk.test.core.databinding.LayoutErrorBinding
import com.marchuk.test.core.databinding.LayoutProgressBinding
import com.marchuk.test.core.presentation.extensions.visible

object BaseDelegates {

    fun loadingDelegateAdapter() =
        adapterDelegateViewBinding<RecyclerDelegate.Loading, DelegateAdapterItem, LayoutProgressBinding>(
            { layoutInflater, root -> LayoutProgressBinding.inflate(layoutInflater, root, false) }
        ) {

            bind {
                binding.progress.visible()
            }

        }

    fun errorDelegateAdapter(callback: () -> Unit) =
        adapterDelegateViewBinding<RecyclerDelegate.Error, DelegateAdapterItem, LayoutErrorBinding>(
            { layoutInflater, root -> LayoutErrorBinding.inflate(layoutInflater, root, false) }
        ) {

            binding.retryButton.setOnClickListener {
                callback()
            }

            bind {
                binding.errorLinear.visible()
            }

        }

}