package com.milad.githoob.ui.profile.organization

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.milad.githoob.data.model.Org
import com.milad.githoob.databinding.ProfileOrgsItemBinding

class ProfileOrgsAdapter(private val viewModel: ProfileOrgsViewModel) :
    ListAdapter<Org, ProfileOrgsAdapter.ViewHolder>(OrgDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ProfileOrgsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: ProfileOrgsViewModel, item: Org) {

            binding.viewmodel = viewModel
            binding.org = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProfileOrgsItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class OrgDiffCallback : DiffUtil.ItemCallback<Org>() {
    override fun areItemsTheSame(oldItem: Org, newItem: Org): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Org, newItem: Org): Boolean {
        return oldItem == newItem
    }
}