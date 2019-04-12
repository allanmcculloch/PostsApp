package com.postsapp.android.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.postsapp.android.R
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Picasso.with(context)
        .load(url)
        .fit()
        .centerInside()
        .placeholder(R.drawable.placeholder)
        .fit()
        .centerInside()
        .into(this)
}

