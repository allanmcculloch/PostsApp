package com.postsapp.android.ui.commentslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.postsapp.android.R
import com.postsapp.android.databinding.FragmentCommentsListBinding
import com.postsapp.android.extensions.inflateBinding
import kotlinx.android.synthetic.main.fragment_comments_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class CommentsListFragment : Fragment() {
    private val viewModel: CommentsListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val postId = arguments?.getInt("postId")

        viewModel.loadData(postId)

        return inflateBinding<FragmentCommentsListBinding>(R.layout.fragment_comments_list, container) { it.vm = viewModel }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        commentsListRecyclerView.setHasFixedSize(true)

        commentsListRecyclerView.layoutManager = LinearLayoutManager(this.context).apply {
            orientation = RecyclerView.VERTICAL
        }
    }
}
