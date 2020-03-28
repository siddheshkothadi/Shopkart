package com.example.tabbedactivity.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tabbedactivity.R
import com.example.tabbedactivity.databinding.ChildGridViewItemBinding
import com.example.tabbedactivity.network.Child
import com.example.tabbedactivity.network.Property

class ChildAdapter: ListAdapter<Child, ChildAdapter.ChildViewHolder>(DiffCallbackk) {

    /**
     * The MarsPropertyViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [MarsProperty] information.
     */
    class ChildViewHolder(private val binding: ChildGridViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bindChild(property: Child) {
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_loading_animation)
                .error(R.drawable.ic_broken_image)
            Glide.with(binding.root)
                .load(property.imgSrcUrl)
                .into(binding.imgView)
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            //binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [MarsProperty]
     * has been updated.
     */
    companion object DiffCallbackk : DiffUtil.ItemCallback<Child>() {
        override fun areItemsTheSame(oldItem: Child, newItem: Child): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Child, newItem: Child): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ChildViewHolder {
        return ChildViewHolder(ChildGridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val property = getItem(position)
        holder.bindChild(property)
    }
}