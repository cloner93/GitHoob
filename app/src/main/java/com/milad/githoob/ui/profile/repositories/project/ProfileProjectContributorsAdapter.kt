package com.milad.githoob.ui.profile.repositories.project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.milad.model.event.Contributor
import com.milad.githoob.databinding.ProfileProjectContributorItemBinding

class ProfileProjectContributorsAdapter(
    private val viewModel: ProfileProjectViewModel,
    private val callback: ((Contributor) -> Unit)?
) :
    ListAdapter<Contributor, ProfileProjectContributorsAdapter.ViewHolder>(ContributorDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item, callback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ProfileProjectContributorItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            viewModel: ProfileProjectViewModel,
            item: Contributor,
            callback: ((Contributor) -> Unit)?
        ) {

            binding.viewmodel = viewModel
            binding.contributor = item
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                binding.contributor?.let {
                    callback?.invoke(it)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProfileProjectContributorItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class ContributorDiffCallback : DiffUtil.ItemCallback<Contributor>() {
    override fun areItemsTheSame(oldItem: Contributor, newItem: Contributor): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Contributor, newItem: Contributor): Boolean {
        return oldItem == newItem
    }
}