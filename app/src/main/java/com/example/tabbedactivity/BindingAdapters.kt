package com.example.tabbedactivity

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tabbedactivity.network.Property
import com.example.tabbedactivity.ui.home.HomeViewModel
import com.example.tabbedactivity.ui.home.PhotoGridAdapter

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
                     data: List<Property>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    val spaceItemDecoration = SpaceItemDecoration(30)
    recyclerView.addItemDecoration(spaceItemDecoration)
    adapter.submitList(data)
}

@BindingAdapter("ApiStatus")
fun bindStatus(statusImageView: ImageView, status: HomeViewModel.ApiStatus?) {
    when (status) {
        HomeViewModel.ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_loading_animation)
        }
        HomeViewModel.ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_broken_image)
        }
        HomeViewModel.ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}