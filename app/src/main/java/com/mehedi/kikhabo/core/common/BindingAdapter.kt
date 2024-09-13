package com.mehedi.kikhabo.core.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mehedi.kikhabo.R

@BindingAdapter("urlToImg")
fun urlToImage(view: ImageView, imageUrl: String) {
    
    val options = RequestOptions.placeholderOf(R.drawable.placeholder).error(R.drawable.placeholder)
    
    Glide.with(view).setDefaultRequestOptions(options).load(imageUrl).into(view)
    
}
