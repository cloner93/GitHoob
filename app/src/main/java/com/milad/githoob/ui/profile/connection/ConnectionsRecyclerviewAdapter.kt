package com.milad.githoob.ui.profile.connection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.milad.githoob.data.model.User
import com.milad.githoob.databinding.ConnectionListItemBinding

class ConnectionsRecyclerviewAdapter(
    private val viewModel: ConnectionViewModel,
    private val callback: ((User) -> Unit)?
) :
    ListAdapter<User, ConnectionsRecyclerviewAdapter.ViewHolder>(UserDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item, callback)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ConnectionListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            viewModel: ConnectionViewModel,
            item: User,
            callback: ((User) -> Unit)?
        ) {

            binding.viewmodel = viewModel
            binding.user = item
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                binding.user?.let {
                    callback?.invoke(it)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ConnectionListItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

}

class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}