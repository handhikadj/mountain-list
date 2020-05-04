package com.example.dika.mountainlist.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dika.mountainlist.R
import com.example.dika.mountainlist.network.ApiStatus

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

    @BindingAdapter("apiStatusSuccess")
    @JvmStatic
    fun loadBtnStatus(view: Button, status: ApiStatus) {
        when(status) {
            ApiStatus.LOADING -> view.visibility = View.GONE
            else -> view.visibility = View.VISIBLE
        }
    }
}

