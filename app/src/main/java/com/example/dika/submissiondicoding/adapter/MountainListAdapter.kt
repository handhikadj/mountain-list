package com.example.dika.submissiondicoding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dika.submissiondicoding.R
import com.example.dika.submissiondicoding.databinding.LayoutMountainListItemBinding
import com.example.dika.submissiondicoding.datasource.Mountain

class MountainListAdapter(
    val clickListener: MountainListAdapterOnClickListener
) : ListAdapter<Mountain, MountainListAdapter.MountainsViewHolder>(MountainListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MountainsViewHolder {
        return MountainsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MountainsViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class MountainsViewHolder(var recyclerItem: LayoutMountainListItemBinding) :
        RecyclerView.ViewHolder(recyclerItem.root) {
        fun bind(item: Mountain, clickListener: MountainListAdapterOnClickListener) {
            recyclerItem.mountain = item
            recyclerItem.clickListener = clickListener
            recyclerItem.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MountainsViewHolder {
                return MountainsViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.layout_mountain_list_item,
                        parent,
                        false
                    )
                )
            }
        }
    }
}

class MountainListDiffCallback : DiffUtil.ItemCallback<Mountain>() {
    override fun areItemsTheSame(oldItem: Mountain, newItem: Mountain): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Mountain, newItem: Mountain): Boolean {
        return oldItem == newItem
    }
}

class MountainListAdapterOnClickListener(val clickListener: (mountainId: Int) -> Unit) {
    fun onClick(mountain: Mountain) = clickListener(mountain.id)
}