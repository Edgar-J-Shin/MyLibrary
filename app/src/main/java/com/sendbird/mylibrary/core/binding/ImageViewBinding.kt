package com.sendbird.mylibrary.core.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sendbird.mylibrary.core.base.GlideApp

object ImageViewBinding {

    @JvmStatic
    @BindingAdapter(value = ["bind:glideUrl"], requireAll = false)
    fun bindGlide(view: ImageView, url: String?) {
        GlideApp.with(view)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(view)
    }
}