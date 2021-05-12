package com.submission.movieandtvshow.di.injector

import com.submission.movieandtvshow.dataobjects.repository.RemoteRepository
import com.submission.movieandtvshow.viewmodelproviders.MainViewModel
import com.submission.movieandtvshow.viewmodelproviders.ShowDetailsViewModel
import com.submission.movieandtvshow.webapi.RetrofitCallback
import com.submission.movieandtvshow.webapi.RetrofitGenerator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField
val fetcherServiceMod = module{
    viewModel { ShowDetailsViewModel(RemoteRepository.getInstance(RetrofitCallback.getInstance(RetrofitGenerator()))) }
    viewModel { MainViewModel(RemoteRepository.getInstance(RetrofitCallback.getInstance(RetrofitGenerator())))  }
}