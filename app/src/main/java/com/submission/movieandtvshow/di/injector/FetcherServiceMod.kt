package com.submission.movieandtvshow.di.injector

import com.submission.movieandtvshow.core.domain.usecase.MovieShowInteraction
import com.submission.movieandtvshow.core.domain.usecase.MovieShowUseCase
import com.submission.movieandtvshow.viewmodelproviders.MainViewModel
import com.submission.movieandtvshow.viewmodelproviders.ShowDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField
val useCaseMod = module {
    single<MovieShowUseCase> { MovieShowInteraction(get()) }
}

val viewModelMod = module {
    viewModel { ShowDetailsViewModel(get()) }
    viewModel { MainViewModel(get()) }
}