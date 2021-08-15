package com.pog.passionfruitOrangeGuava.features.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pog.passionfruitOrangeGuava.BR
import com.pog.passionfruitOrangeGuava.R
import com.pog.passionfruitOrangeGuava.features.search.viewmodels.SearchViewModel

class SearchFragment: Fragment() {
    val layoutResourceId: Int
        get() = R.layout.fragment_search

    val viewModel: SearchViewModel
        get() = ViewModelProvider(this).get(SearchViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            inflater,
            layoutResourceId,
            container,
            false
        )
        binding.setVariable(BR.viewmodel, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}