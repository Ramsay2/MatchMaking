package com.sachin.matchmaking.ui.adapter.selected_profiles_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sachin.matchmaking.databinding.ItemMatchPersonBinding
import com.sachin.matchmaking.ui.adapter.entity.MatchProfileEntity

class SelectedProfilesAdapter : RecyclerView.Adapter<SelectedProfilesAdapter.SelectedProfilesViewHolder>() {

    private var profiles = emptyList<MatchProfileEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedProfilesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMatchPersonBinding.inflate(inflater, parent, false)
        return SelectedProfilesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectedProfilesViewHolder, position: Int) {
        holder.bind(profiles[position])
    }

    override fun getItemCount(): Int = profiles.size

    fun submitList(newList: List<MatchProfileEntity>) {
        val diffCallback = MatchProfileDiffCallback(profiles, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        profiles = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class SelectedProfilesViewHolder(
        private val binding: ItemMatchPersonBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MatchProfileEntity) {
            binding.profile = item
            binding.executePendingBindings()
        }
    }
}


class MatchProfileDiffCallback(
    private val oldList: List<MatchProfileEntity>,
    private val newList: List<MatchProfileEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id || oldItem.profileStatus == newItem.profileStatus
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
