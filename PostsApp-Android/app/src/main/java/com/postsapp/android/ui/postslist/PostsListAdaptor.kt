package com.postsapp.android.ui.postslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.postsapp.android.R
import com.postsapp.android.databinding.ItemPostBinding
import com.postsapp.android.model.Post

class PostsListAdapter: RecyclerView.Adapter<PostsListAdapter.ViewHolder>() {
    private lateinit var postsList: List<Post>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPostBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postsList[position])
    }

    override fun getItemCount(): Int {
        return if (::postsList.isInitialized) postsList.size else 0
    }

    fun updateList(postList: List<Post>) {
        this.postsList = postList

        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = PostViewModel()

        fun bind(post: Post) {
            itemView.setOnClickListener {
                var id = post.id

                var bundle = bundleOf("postId" to id)

                findNavController(itemView).navigate(R.id.action_postsListFragment_to_postDetailFragment, bundle)
            }
            viewModel.bind(post)
            binding.viewModel = viewModel
        }
    }
}