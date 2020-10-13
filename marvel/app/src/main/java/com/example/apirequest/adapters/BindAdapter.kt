package com.example.apirequest.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.apirequest.R


@BindingAdapter(value = ["urlToImage","ext"], requireAll = true)
//Generic: Inherit from View
//Parameters: Element where is going to be used,
fun convertImageFromUrl(imageView: ImageView, url: String, ext: String) {
    Glide.with(imageView.context)
        .load(url.plus(".").plus(ext))
        .placeholder(R.drawable.ic_file_download_black_24dp)
        .into(imageView)
}

