package com.pog.passionfruitOrangeGuava.ui

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pog.passionfruitOrangeGuava.features.search.model.UserModel
import com.pog.passionfruitOrangeGuava.features.search.ui.SearchListAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("setAdapter")
fun setAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    recyclerView.layoutManager = LinearLayoutManager(
        recyclerView.context,
        LinearLayoutManager.VERTICAL,
        false
    )
    recyclerView.adapter = adapter
}

@BindingAdapter("setItems")
fun setItems(recyclerView: RecyclerView, items: List<UserModel>?) {
    if (items == null) return
    (recyclerView.adapter as SearchListAdapter?)?.let {
        Log.d("paco", items.firstOrNull().toString())
        it.items = items
    }
}

@BindingAdapter("setImageURL")
fun setImageURL(view: ImageView, imageURL: String) {
    Picasso.get()
        .load(imageURL)
        .error(Log.e("ImageView", "Error loading image $imageURL"))
        .into(view)
}