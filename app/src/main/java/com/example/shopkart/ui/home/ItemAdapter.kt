package com.example.shopkart.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopkart.databinding.GridViewItemBinding
import com.example.shopkart.model.ItemTypeModel

class ItemAdapter : ListAdapter<ItemTypeModel, ItemAdapter.ItemViewHolder>(DiffCallback) {


    class ItemViewHolder(private var binding: GridViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemTypeModel) {
            binding.property = item
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ItemTypeModel>() {
        override fun areItemsTheSame(oldItem: ItemTypeModel, newItem: ItemTypeModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ItemTypeModel, newItem: ItemTypeModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ItemViewHolder {
        return ItemViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}