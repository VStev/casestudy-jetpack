package com.submission.movieandtvshow.favourite

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@JvmField
val favouriteModule = module {
    viewModel { FavouriteViewModel(get()) }
}