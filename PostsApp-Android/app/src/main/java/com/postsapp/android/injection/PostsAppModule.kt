package com.postsapp.android.injection

import com.postsapp.android.network.ClientProvider
import com.postsapp.android.repository.PostsRepository
import com.postsapp.android.repository.UsersRepository
import com.postsapp.android.repository.remote.PostsApiService
import com.postsapp.android.ui.postdetail.PostDetailViewModel
import com.postsapp.android.ui.postslist.PostsListViewModel
import com.postsapp.android.ui.userslist.UsersListViewModel
import com.postsapp.android.usecases.GetPostUseCase
import com.postsapp.android.usecases.GetPostsListUseCase
import com.postsapp.android.usecases.GetUsersUseCase
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val PostsAppModule = module {
    viewModel { PostDetailViewModel(get()) }
    viewModel { PostsListViewModel(get()) }
    viewModel { UsersListViewModel(get()) }

    single { GetPostsListUseCase(get()) }
    single { GetUsersUseCase(get()) }
    single { GetPostUseCase(get()) }

    single { PostsRepository(get()) }
    single { UsersRepository(get()) }

    single { PostsApiService(get()) }
    single { ClientProvider() }
}