package com.marchuk.test.core.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.marchuk.test.core.databinding.LayoutErrorViewBinding
import com.marchuk.test.core.presentation.extensions.gone
import com.marchuk.test.core.presentation.extensions.visible

class ErrorView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    private var binding: LayoutErrorViewBinding = LayoutErrorViewBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    fun setupAction(listener: OnClickListener) {
        binding.errorLayout.retryButton.setOnClickListener(listener)
    }

    fun hide() {
        with(binding) {
            errorLayout.errorLinear.gone()
            progressLayout.progress.gone()
        }
    }

    fun progress() {
        with(binding) {
            errorLayout.errorLinear.gone()
            progressLayout.progress.visible()
        }
    }

    fun error() {
        with(binding) {
           errorLayout.errorLinear.visible()
           progressLayout.progress.gone()
        }
    }

}
