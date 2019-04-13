package com.postsapp.android.injection

import com.postsapp.android.network.ClientProvider
import com.postsapp.android.repository.CommentsRepository
import com.postsapp.android.repository.PostsRepository
import com.postsapp.android.repository.UsersRepository
import com.postsapp.android.repository.remote.PostsApiService
import com.postsapp.android.ui.commentslist.CommentsListViewModel
import com.postsapp.android.ui.postdetail.PostDetailViewModel
import com.postsapp.android.ui.postslist.PostsListViewModel
import com.postsapp.android.ui.userslist.UsersListViewModel
import com.postsapp.android.usecases.*
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val PostsAppModule = module {
    viewModel { PostDetailViewModel(get()) }
    viewModel { PostsListViewModel(get()) }
    viewModel { UsersListViewModel(get()) }
    viewModel { CommentsListViewModel(get(), get()) }

    single { GetPostsListUseCase(get()) }
    single { GetUsersUseCase(get()) }
    single { GetPostUseCase(get()) }
    single { GetPostDetailUseCase(get(),get(),get()) }
    single { GetCommentsUseCase(get()) }
    single { GetCommentsByPostIdUseCase(get()) }

    single { PostsRepository(get()) }
    single { UsersRepository(get()) }
    single { CommentsRepository(get()) }

    single { PostsApiService(get()) }
    single { ClientProvider() }
}