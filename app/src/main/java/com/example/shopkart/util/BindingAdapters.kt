package com.example.shopkart.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.shopkart.R
import com.example.shopkart.databinding.FragmentHomeBinding
import com.example.shopkart.domain.CartModel
import com.example.shopkart.domain.ItemTypeModel
import com.example.shopkart.ui.SpaceItemDecoration
import com.example.shopkart.ui.cart.CartAdapter
import com.example.shopkart.ui.home.ItemAdapter
import com.example.shopkart.viewmodels.home.HomeViewModel
import java.lang.NullPointerException

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<ItemTypeModel>?
) {
    val adapter = recyclerView.adapter as ItemAdapter
    adapter.submitList(data)
}

@BindingAdapter("listCartData")
fun bindCartRecyclerView(
    recyclerView: RecyclerView,
    data: List<CartModel>?
) {
    val adapter = recyclerView.adapter as CartAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(progressBar: ProgressBar, status: HomeViewModel.ApiStatus?) {
    when (status) {
        HomeViewModel.ApiStatus.DONE -> progressBar.visibility = View.GONE
        HomeViewModel.ApiStatus.LOADING -> progressBar.visibility = View.VISIBLE
        HomeViewModel.ApiStatus.ERROR -> progressBar.visibility = View.GONE
    }
}

@BindingAdapter("padding")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    padding: Int
) {
    val spaceItemDecoration = SpaceItemDecoration(padding)
    recyclerView.addItemDecoration(spaceItemDecoration)
}

@BindingAdapter("button1G","button2R","button2G","button3R","button3G","cart1")
fun visibility1(
    button1R: ConstraintLayout,
    button1G: ConstraintLayout,
    button2R: ConstraintLayout,
    button2G: ConstraintLayout,
    button3R: ConstraintLayout,
    button3G: ConstraintLayout,
    cartItems: List<CartModel>?
) {
    if (cartItems.isNullOrEmpty()) {
        //1
        button1R.visibility=View.VISIBLE
        button1G.visibility=View.GONE
        //2
        button2R.visibility=View.VISIBLE
        button2G.visibility=View.GONE
        //3
        button3R.visibility=View.VISIBLE
        button3G.visibility=View.GONE
    } else {
        for (items in cartItems) {
            if (items.id == "1") {
                button1R.visibility=View.GONE
                button1G.visibility=View.VISIBLE
            }
            if (items.id == "2") {
                button2R.visibility=View.GONE
                button2G.visibility=View.VISIBLE
            }
            if (items.id == "3") {
                button3R.visibility=View.GONE
                button3G.visibility=View.VISIBLE
            }
        }
    }
}