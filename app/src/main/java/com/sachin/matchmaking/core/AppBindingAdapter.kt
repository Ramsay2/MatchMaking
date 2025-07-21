package com.sachin.matchmaking.core

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sachin.matchmaking.R

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_background)
        .into(this)
}
