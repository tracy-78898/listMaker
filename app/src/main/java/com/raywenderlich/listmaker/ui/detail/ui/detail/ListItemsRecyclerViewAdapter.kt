package com.raywenderlich.listmaker.ui.detail.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.listmaker.TaskList
import com.raywenderlich.listmaker.databinding.ListItemViewHolderBinding

private var LinearLayout.text: String
    get() {
        TODO("Not yet implemented")
    }
    set() {}

class ListItemsRecyclerViewAdapter(var list: TaskList) :
    RecyclerView.Adapter<ListItemViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType:
    Int): ListItemViewHolder {
        val binding = ListItemViewHolderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        return ListItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return list.tasks.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.binding.textViewTask.text=list.tasks[position]}
}