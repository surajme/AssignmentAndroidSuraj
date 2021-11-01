package com.assignment.android.ui.main.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load


@BindingAdapter("data")
fun <T> setRecyclerViewItems(recyclerView: RecyclerView, data: T) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as? BindableAdapter<T>)?.setData(data)
    }
}

@BindingAdapter("url")
fun loadImageFromURL(imageView: ImageView, url: String) {
    imageView.load(url) {
        crossfade(true)
        crossfade(500)
    }
}
