package com.milad.githoob.ui.profile.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.milad.githoob.data.model.event.Repo
import com.milad.githoob.databinding.ProfileRepositoriesItemBinding

class ProfileRepositoryAdapter(
    private val viewModel: ProfileRepositoriesViewModel,
    private val callback: ((Repo) -> Unit)?
) :
    ListAdapter<Repo, ProfileRepositoryAdapter.ViewHolder>(RepoDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item, callback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ProfileRepositoriesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            viewModel: ProfileRepositoriesViewModel,
            item: Repo,
            callback: ((Repo) -> Unit)?
        ) {

            binding.viewmodel = viewModel
            binding.repo = item
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                binding.repo?.let {
                    callback?.invoke(it)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProfileRepositoriesItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class RepoDiffCallback : DiffUtil.ItemCallback<Repo>() {
    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem == newItem
    }
}