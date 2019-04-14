package com.postsapp.android.ui.userslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.postsapp.android.R
import com.postsapp.android.databinding.ItemUserBinding
import com.postsapp.android.model.User

class UsersListAdapter : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {
    private lateinit var userList: List<User>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemUserBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_user, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return if (::userList.isInitialized) userList.size else 0
    }

    fun updateList(users: List<User>) {
        this.userList = users

        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = UserViewModel()

        fun bind(user: User) {
            viewModel.bind(user)
            binding.viewModel = viewModel
        }
    }
}
