package com.example.tabbedactivity.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tabbedactivity.databinding.GridViewItem2Binding
import com.example.tabbedactivity.databinding.GridViewItem3Binding
import com.example.tabbedactivity.network.Property2
import com.example.tabbedactivity.network.Property3

class Adapter3 : ListAdapter<Property3, Adapter3.Property3ViewHolder>(DiffCallback) {

    /**
     * The MarsPropertyViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [MarsProperty] information.
     */
    class Property3ViewHolder(private var binding: GridViewItem3Binding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(property: Property3) {
            binding.property = property
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
            /*val imgUri = property.imgSrcUrl.toUri().buildUpon().scheme("https").build()

            binding.blogTitle.text = property.name
            binding.blogAuthor.text = property.descr
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_loading_animation)
                .error(R.drawable.ic_broken_image)
            Glide.with(binding.root)
                .applyDefaultRequestOptions(requestOptions)
                .load(imgUri)
                .into(binding.blogImage)*/
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [MarsProperty]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Property3>() {
        override fun areItemsTheSame(oldItem: Property3, newItem: Property3): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Property3, newItem: Property3): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): Property3ViewHolder {
        return Property3ViewHolder(GridViewItem3Binding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: Property3ViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.bind(marsProperty)
    }
}