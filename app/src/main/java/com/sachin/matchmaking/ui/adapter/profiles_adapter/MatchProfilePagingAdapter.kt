package com.sachin.matchmaking.ui.adapter.profiles_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sachin.matchmaking.databinding.ItemMatchPersonBinding
import com.sachin.matchmaking.ui.adapter.entity.MatchProfileEntity

object MatchProfileDiffCallback : DiffUtil.ItemCallback<MatchProfileEntity>() {
    override fun areItemsTheSame(
        oldItem: MatchProfileEntity,
        newItem: MatchProfileEntity
    ): Boolean {
        return oldItem.id == newItem.id || oldItem.profileStatus == newItem.profileStatus
    }

    override fun areContentsTheSame(
        oldItem: MatchProfileEntity,
        newItem: MatchProfileEntity
    ): Boolean {
        return oldItem == newItem
    }
}

class MatchProfilePagingAdapter :
    PagingDataAdapter<MatchProfileEntity, MatchProfilePagingAdapter.MatchProfileViewHolder>(
        MatchProfileDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchProfileViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMatchPersonBinding.inflate(inflater, parent, false)
        return MatchProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchProfileViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class MatchProfileViewHolder(
        private val binding: ItemMatchPersonBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MatchProfileEntity) {
            binding.profile = item
            binding.executePendingBindings()
        }
    }
}

