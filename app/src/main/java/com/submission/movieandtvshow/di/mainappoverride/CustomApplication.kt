package com.submission.movieandtvshow.di.mainappoverride

import android.app.Application
import com.submission.movieandtvshow.core.di.injector.dbModule
import com.submission.movieandtvshow.core.di.injector.netModule
import com.submission.movieandtvshow.core.di.injector.repoModule
import com.submission.movieandtvshow.di.injector.*
import com.submission.movieandtvshow.di.injector.useCaseMod
import com.submission.movieandtvshow.di.injector.viewModelMod
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CustomApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@CustomApplication)
            //add modules here
            modules(listOf(
                netModule,
                dbModule,
                repoModule,
                viewModelMod,
                useCaseMod
            ))
        }
    }
}