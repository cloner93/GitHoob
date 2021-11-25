package com.milad.githoob.ui.profile.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.milad.githoob.data.model.event.Events
import com.milad.githoob.databinding.ProfileFeedsItemBinding

class ProfileFeedsAdapter(private val viewModel: ProfileFeedsViewModel) :
    ListAdapter<Events, ProfileFeedsAdapter.ViewHolder>(EventsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    class ViewHolder private constructor(val binding: ProfileFeedsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: ProfileFeedsViewModel, item: Events) {
            binding.viewmodel = viewModel
            binding.event = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProfileFeedsItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class EventsDiffCallback : DiffUtil.ItemCallback<Events>() {
    override fun areItemsTheSame(oldItem: Events, newItem: Events): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Events, newItem: Events): Boolean {
        return oldItem == newItem
    }
}