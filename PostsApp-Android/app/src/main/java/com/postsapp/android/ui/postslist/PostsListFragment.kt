package com.postsapp.android.ui.postslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.postsapp.android.R
import com.postsapp.android.extensions.inflateBinding
import com.postsapp.android.databinding.FragmentPostsListBinding
import kotlinx.android.synthetic.main.fragment_posts_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class PostsListFragment : Fragment() {
    private val viewModel: PostsListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflateBinding<FragmentPostsListBinding>(R.layout.fragment_posts_list, container) { it.viewModel = viewModel }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postsListRecyclerView.setHasFixedSize(true)

        postsListRecyclerView.layoutManager = LinearLayoutManager(this.context).apply {
            orientation = RecyclerView.VERTICAL
        }
    }
}
