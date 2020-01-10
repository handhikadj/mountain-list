package com.example.dika.submissiondicoding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dika.submissiondicoding.R
import com.example.dika.submissiondicoding.databinding.LayoutTodoListItemBinding
import com.example.dika.submissiondicoding.models.Todo

class TodoListAdapter(
    val clickListener: TodoListAdapterOnClickListener
) : PagedListAdapter<Todo, TodoListAdapter.TodosViewHolder>(TodoListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosViewHolder {
        return TodosViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TodosViewHolder, position: Int) {
        val data = getItem(position)
        data?.let {
            holder.bind(it, clickListener)
        }
    }

    class TodosViewHolder(var recyclerItem: LayoutTodoListItemBinding) :
        RecyclerView.ViewHolder(recyclerItem.root) {
        fun bind(item: Todo, clickListener: TodoListAdapterOnClickListener) {
            recyclerItem.todo = item
            recyclerItem.clickListener = clickListener
            recyclerItem.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): TodosViewHolder {
                return TodosViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.layout_todo_list_item,
                        parent,
                        false
                    )
                )
            }
        }
    }
}

class TodoListDiffCallback : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }
}

class TodoListAdapterOnClickListener(val clickListener: (todoId: Int) -> Unit) {
    fun onClick(todo: Todo) = clickListener(todo.id)
}