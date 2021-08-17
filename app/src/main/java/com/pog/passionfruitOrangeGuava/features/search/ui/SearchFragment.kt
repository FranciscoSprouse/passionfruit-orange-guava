package com.pog.passionfruitOrangeGuava.features.search.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.pog.passionfruitOrangeGuava.BR
import com.pog.passionfruitOrangeGuava.R
import com.pog.passionfruitOrangeGuava.features.search.viewmodels.SearchViewModel
import com.pog.passionfruitOrangeGuava.ui.MainActivity
import javax.inject.Inject

class SearchFragment: Fragment() {
    val layoutResourceId: Int
        get() = R.layout.fragment_search

    @Inject lateinit var viewModel: SearchViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })

        viewModel.isLoading.observe(viewLifecycleOwner, {
            Log.d("paco", it.toString())
        })

        viewModel.userList.observe(viewLifecycleOwner, {
            Log.d("paco", it.firstOrNull().toString())
        })
    }
}