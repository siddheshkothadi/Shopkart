package com.example.tabbedactivity.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tabbedactivity.databinding.GridViewItemBinding
import com.example.tabbedactivity.network.Property

class PhotoGridAdapter : ListAdapter<Property, PhotoGridAdapter.MarsPropertyViewHolder>(DiffCallback) {

    /**
     * The MarsPropertyViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [MarsProperty] information.
     */
    class MarsPropertyViewHolder(private var binding: GridViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: Property) {
            binding.property = marsProperty
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [MarsProperty]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Property>() {
        override fun areItemsTheSame(oldItem: Property, newItem: Property): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Property, newItem: Property): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MarsPropertyViewHolder {
        return MarsPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.bind(marsProperty)
    }
}