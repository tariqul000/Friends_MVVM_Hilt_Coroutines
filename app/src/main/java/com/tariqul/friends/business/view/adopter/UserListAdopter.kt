package com.tariqul.friends.business.view.adopter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tariqul.friends.R
import com.tariqul.friends.business.model.BaseUsersDataModel
import com.tariqul.friends.business.model.Results
import com.tariqul.friends.databinding.GridItemBinding
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@ActivityScoped
class UsersListAdapter @Inject constructor(private val clickListener: ClickListener) :
    ListAdapter<Results, UsersListAdapter.ViewHolder>(UsersListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: GridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Results, clickListener: ClickListener) {
            binding.data = item
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GridItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class UsersListDiffCallback : DiffUtil.ItemCallback<Results>() {

    override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem == newItem
    }

}
//
class ClickListener @Inject constructor() {

    var onItemClick: ((Results) -> Unit)? = null

    fun onClick(data: Results) {
        onItemClick?.invoke(data)
    }
}