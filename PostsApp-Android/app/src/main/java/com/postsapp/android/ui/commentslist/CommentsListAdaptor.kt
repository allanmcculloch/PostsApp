package com.postsapp.android.ui.commentslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.postsapp.android.R
import com.postsapp.android.databinding.ItemCommentBinding
import com.postsapp.android.model.Comment

class CommentsListAdapter : RecyclerView.Adapter<CommentsListAdapter.ViewHolder>() {
    private lateinit var commentsList: List<Comment>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCommentBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_comment, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commentsList[position])
    }

    override fun getItemCount(): Int {
        return if (::commentsList.isInitialized) commentsList.size else 0
    }

    fun updateList(commentList: List<Comment>) {
        this.commentsList = commentList

        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = CommentViewModel()

        fun bind(comment: Comment) {
            viewModel.bind(comment)
            binding.viewModel = viewModel
        }
    }
}
