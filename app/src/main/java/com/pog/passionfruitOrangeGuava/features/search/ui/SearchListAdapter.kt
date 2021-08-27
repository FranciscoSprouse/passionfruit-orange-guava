package com.pog.passionfruitOrangeGuava.features.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.pog.passionfruitOrangeGuava.BR
import com.pog.passionfruitOrangeGuava.R
import com.pog.passionfruitOrangeGuava.features.search.model.UserModel

class SearchListAdapter() : RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater,
            viewType,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    var items: List<UserModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.view_search_list_item
    }

    class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Any?) {
            binding.setVariable(BR.obj, item)
            binding.executePendingBindings()
        }
    }
}