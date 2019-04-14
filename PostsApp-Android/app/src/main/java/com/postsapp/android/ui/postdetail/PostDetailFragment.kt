package com.postsapp.android.ui.postdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.postsapp.android.R
import com.postsapp.android.extensions.inflateBinding
import com.postsapp.android.databinding.FragmentPostDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class PostDetailFragment : Fragment() {
    private val viewModel: PostDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var postId = arguments?.getInt("postId")

        viewModel.loadData(postId!!)

        return inflateBinding<FragmentPostDetailBinding>(R.layout.fragment_post_detail, container) { it.viewModel = viewModel }
    }
}