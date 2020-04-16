package com.example.shopkart.ui.account

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopkart.databinding.AccountItemBinding
import com.example.shopkart.domain.AccountModel

class AccountAdapter : ListAdapter<AccountModel, AccountAdapter.ItemViewHolder>(DiffCallback) {


    class ItemViewHolder(private var binding: AccountItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AccountModel) {
            binding.order = item
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<AccountModel>() {
        override fun areItemsTheSame(oldItem: AccountModel, newItem: AccountModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: AccountModel, newItem: AccountModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        return ItemViewHolder(AccountItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}