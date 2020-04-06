package com.example.shopkart.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.shopkart.R
import com.example.shopkart.domain.ItemTypeModel
import com.example.shopkart.network.ItemType
import com.example.shopkart.ui.SpaceItemDecoration
import com.example.shopkart.viewmodels.home.HomeViewModel
import com.example.shopkart.ui.home.ItemAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.ic_loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<ItemTypeModel>?) {
    val adapter = recyclerView.adapter as ItemAdapter
    val spaceItemDecoration = SpaceItemDecoration(30)
    recyclerView.addItemDecoration(spaceItemDecoration)
    adapter.submitList(data)
}

/*@BindingAdapter("apiStatus")
fun bindStatus(progressBar: ProgressBar, status: HomeViewModel.ApiStatus?) {
    when (status) {
        HomeViewModel.ApiStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
        HomeViewModel.ApiStatus.ERROR -> {
            progressBar.visibility = View.GONE
        }
        HomeViewModel.ApiStatus.DONE -> {
            progressBar.visibility = View.GONE
        }
    }
}

@BindingAdapter("scrollApiStatus")
fun bindStatus(scrollView: ScrollView, status: HomeViewModel.ApiStatus?) {
    when (status) {
        HomeViewModel.ApiStatus.DONE -> {
            scrollView.visibility = View.VISIBLE
        }
    }
}*/


