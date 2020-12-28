package com.marchuk.test.core.presentation.mvvm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.marchuk.test.core.R

abstract class BaseFragment<Binding : ViewBinding>(@LayoutRes layoutIdRes: Int) : Fragment(layoutIdRes) {

    protected var _binding: Binding? = null
    val binding: Binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel()
    }

    abstract fun observeViewModel()

    abstract fun setupUi(savedInstanceState: Bundle?)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun showToast() {
        Toast.makeText(requireContext(), getString(R.string.error_message), Toast.LENGTH_LONG).show()
    }

}