package com.example.dika.mountainlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dika.mountainlist.R
import com.example.dika.mountainlist.databinding.LayoutTodoListItemBinding
import com.example.dika.mountainlist.models.Todo

class TodoListAdapter :
    PagedListAdapter<Todo, TodoListAdapter.TodosViewHolder>(TodoListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosViewHolder {
        return TodosViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TodosViewHolder, position: Int) {
        val todoData = getItem(position)
        todoData?.let {
            holder.bind(it)
        }
    }

    class TodosViewHolder(var recyclerItem: LayoutTodoListItemBinding) :
        RecyclerView.ViewHolder(recyclerItem.root) {
        fun bind(item: Todo) {
            recyclerItem.todo = item
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