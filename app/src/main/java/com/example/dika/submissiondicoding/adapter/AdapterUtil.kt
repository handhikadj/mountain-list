package com.example.dika.submissiondicoding.adapter

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dika.submissiondicoding.R
import com.example.dika.submissiondicoding.network.ApiStatus

object AdapterUtil {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, imageUrl: String) {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)

        Glide.with(view.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(imageUrl)
            .into(view)
    }

    @BindingAdapter("apiStatus")
    @JvmStatic
    fun loadStatus(view: ProgressBar, status: ApiStatus) {
        when(status) {
            ApiStatus.LOADING -> view.visibility = View.VISIBLE
            else -> view.visibility = View.GONE
        }
    }
}

