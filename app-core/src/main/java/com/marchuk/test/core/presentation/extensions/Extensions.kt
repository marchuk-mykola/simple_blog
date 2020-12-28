package com.marchuk.test.core.presentation.extensions

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.marchuk.test.core.R

fun RecyclerView.addDivider16dp() {
    this.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
        setDrawable(ContextCompat.getDrawable(context, R.drawable.divider_16dp)!!)
    })
}

fun View.visible() {
    if (visibility != View.VISIBLE)
        visibility = View.VISIBLE
}

fun View.gone() {
    if (visibility != View.GONE)
        visibility = View.GONE
}