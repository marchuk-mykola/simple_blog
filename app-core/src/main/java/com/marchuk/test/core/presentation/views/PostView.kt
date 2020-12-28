package com.marchuk.test.core.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.marchuk.test.core.R
import com.marchuk.test.core.databinding.LayoutPostBinding
import com.marchuk.test.core.domain.models.Post

class PostView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val ANIMATION_DURATION = 200L
    }

    private var binding: LayoutPostBinding = LayoutPostBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    fun setListener(listener: OnClickListener) {
        binding.root.setOnClickListener(listener)
    }

    fun bindView(post: Post, layoutPosition: Int = 0) {
        prepareView(post.isExpanded)
        setData(post, layoutPosition)
    }

    private fun prepareView(isExpanded: Boolean) {
        with(binding) {
            content.setAnimationDuration(0)
            if (isExpanded) {
                content.expand()
                arrowDown.rotation = 180f
            } else {
                content.collapse()
                arrowDown.rotation = 0f
            }
        }
    }

    private fun setData(post: Post, layoutPosition: Int) {
        with(binding) {
            root.setBackgroundColor(getBackgroundColor(layoutPosition))
            title.text = post.title
            content.text = post.body
            arrowDown.setOnClickListener {
                content.apply {
                    setAnimationDuration(200)
                    toggle()
                }
                arrowDown.rotate(post.isExpanded)
                post.isExpanded = post.isExpanded.not()
            }
        }
    }

    private fun ImageView.rotate(isExpanded: Boolean) {
        if (isExpanded) {
            this.animate().setDuration(ANIMATION_DURATION).rotation(0f).start()
        } else {
            this.animate().setDuration(ANIMATION_DURATION).rotation(180f).start()
        }
    }

    private fun getBackgroundColor(position: Int): Int {
        return ContextCompat.getColor(context,
            if (position % 2 == 0)
                android.R.color.white
            else
                R.color.grey
        )
    }

}