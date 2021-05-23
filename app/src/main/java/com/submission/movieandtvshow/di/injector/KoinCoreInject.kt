package com.submission.movieandtvshow.di.injector

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.submission.movieandtvshow.dataobjects.repository.LocalDataSource
import com.submission.movieandtvshow.dataobjects.repository.RemoteRepository
import com.submission.movieandtvshow.dataobjects.room.EntertainmentDatabase
import com.submission.movieandtvshow.utilities.AppExecutors
import com.submission.movieandtvshow.webapi.RemoteDataSource
import com.submission.movieandtvshow.webapi.RetrofitInterfaces
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@JvmField
val dbModule = module {
    factory { get<EntertainmentDatabase>().entertainmentDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            EntertainmentDatabase::class.java,
            "Entertainment.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}

val netModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        retrofit.create(RetrofitInterfaces::class.java)
    }
}

val repoModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single { AppExecutors() }
    single { RemoteRepository(get(), get(), get()) }
}