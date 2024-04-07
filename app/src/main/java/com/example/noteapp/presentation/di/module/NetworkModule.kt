package com.example.noteapp.presentation.di.module

import com.example.noteapp.data.network.ApiService
import com.example.noteapp.utils.Constants
import com.google.firebase.BuildConfig
import com.training.traveling.presentation.di.annotation.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    //HttpLoggingInterceptor mới và thiết lập level của interceptor để ghi lại thông tin request và response của HTTP với mức độ BODY.
    @ApplicationScope
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @ApplicationScope
    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) builder.addInterceptor(interceptor)
        return builder.build()
    }

    @ApplicationScope
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    @ApplicationScope
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}