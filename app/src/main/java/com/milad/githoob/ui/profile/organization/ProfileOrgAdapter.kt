package com.milad.githoob.ui.profile.organization

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.milad.githoob.data.model.Org
import com.milad.githoob.databinding.ProfileOrgItemBinding

class ProfileOrgAdapter(private val viewModel: ProfileOrgViewModel) :
    ListAdapter<Org, ProfileOrgAdapter.ViewHolder>(OrgDiffCallback()) {

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

    class ViewHolder private constructor(val binding: ProfileOrgItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: ProfileOrgViewModel, item: Org) {

            binding.viewmodel = viewModel
            binding.org = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProfileOrgItemBinding.inflate(layoutInflater, parent, false)

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