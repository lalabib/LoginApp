package com.lalabib.loginapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lalabib.loginapp.R

object SharedObject {
    const val BASE_URL = "https://reqres.in/"

    fun loadAvatar(imageView: ImageView, avatar: String) {
        Glide.with(imageView.context)
            .load(avatar)
            .circleCrop()
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_broken_img)
            )
            .into(imageView)
    }
}