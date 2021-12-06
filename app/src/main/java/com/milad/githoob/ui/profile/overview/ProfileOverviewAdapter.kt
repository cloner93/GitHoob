package com.milad.githoob.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.milad.githoob.data.model.event.Event
import com.milad.githoob.databinding.ProfileActivityItemsBinding
import com.milad.githoob.ui.profile.overview.ProfileOverviewViewModel

class ProfileOverviewAdapter(private val viewModel: ProfileOverviewViewModel) :
    ListAdapter<Event, ProfileOverviewAdapter.ViewHolder>(EventDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder(val binding: ProfileActivityItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: ProfileOverviewViewModel, item: Event) {

            binding.viewmodel = viewModel
            binding.event = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProfileActivityItemsBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class EventDiffCallback : DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }
}