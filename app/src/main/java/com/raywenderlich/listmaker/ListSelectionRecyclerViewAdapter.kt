package com.raywenderlich.listmaker

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView



class ListSelectionRecyclerViewAdapter(val lists:
                                       MutableList<TaskList>, val clickListener:
                                       ListSelectionRecyclerViewClickListener) :
    RecyclerView.Adapter<ListSelectionViewHolder>() {


        fun listsUpdated() {
            notifyItemInserted(lists.size-1)
        }



        override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
            holder.binding.itemNumber.text = (position + 1).toString()
            holder.binding.itemString.text = lists[position].name
            holder.itemView.setOnClickListener { clickListener.listItemClicked(lists[position]) }

        }
        interface ListSelectionRecyclerViewClickListener {
            fun listItemClicked(list: TaskList)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
            TODO("Not yet implemented")
        }


        override fun getItemCount(): Int {
            return lists.size
        }
    }



