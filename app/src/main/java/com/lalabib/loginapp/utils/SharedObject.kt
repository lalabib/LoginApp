package com.lalabib.loginapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

object SharedObject {
    const val BASE_URL = "https://reqres.in/"

    fun loadAvatar(imageView: ImageView, avatar: String) {
        Glide.with(imageView.context)
            .load(avatar)
            .circleCrop()
            .into(imageView)
    }
}