package com.lalabib.loginapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lalabib.loginapp.databinding.ItemListBinding
import com.lalabib.loginapp.domain.model.ListUser
import com.lalabib.loginapp.utils.SharedObject.loadAvatar

class ListUserAdapter : PagingDataAdapter<ListUser, ListUserAdapter.ViewHolder>(ListUserDiffUtil) {

    private object ListUserDiffUtil : DiffUtil.ItemCallback<ListUser>() {
        override fun areItemsTheSame(oldItem: ListUser, newItem: ListUser): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ListUser, newItem: ListUser): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user)
        }
    }

    class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ListUser) {
            binding.apply {
                tvFirstName.text = user.firstName
                tvLastName.text = user.lastName
                loadAvatar(ivAvatar, user.avatar)
            }
        }
    }
}