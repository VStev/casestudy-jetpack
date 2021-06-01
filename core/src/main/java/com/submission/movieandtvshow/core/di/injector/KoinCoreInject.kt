package com.submission.movieandtvshow.core.di.injector

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.submission.movieandtvshow.core.dataobjects.repository.LocalDataSource
import com.submission.movieandtvshow.core.dataobjects.room.EntertainmentDatabase
import com.submission.movieandtvshow.core.utilities.AppExecutors
import com.submission.movieandtvshow.core.webapi.RemoteDataSource
import com.submission.movieandtvshow.core.webapi.RetrofitInterfaces
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("hxxrdlt4".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            EntertainmentDatabase::class.java,
            "Entertainment.db"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val netModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .add(hostname, "sha256/KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I=")
            .build()
        OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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
            .client(get())
            .build()
        retrofit.create(RetrofitInterfaces::class.java)
    }
}

val repoModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single { AppExecutors() }
    single {
        com.submission.movieandtvshow.core.dataobjects.repository.RemoteRepository(
            get(),
            get(),
            get()
        )
    }
}