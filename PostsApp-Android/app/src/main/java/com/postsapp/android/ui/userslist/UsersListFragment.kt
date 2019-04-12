package com.postsapp.android.ui.userslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.postsapp.android.R
import com.postsapp.android.extensions.inflateBinding
import org.koin.android.viewmodel.ext.android.viewModel
import com.postsapp.android.databinding.FragmentUsersBinding
import kotlinx.android.synthetic.main.fragment_users.*

class UsersListFragment : Fragment() {
    private val viewModel: UsersListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflateBinding<FragmentUsersBinding>(R.layout.fragment_users, container) { it.viewModel = viewModel }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usersListRecyclerView.setHasFixedSize(true)

        usersListRecyclerView.layoutManager = LinearLayoutManager(this.context).apply {
            orientation = RecyclerView.VERTICAL
        }
    }
}
