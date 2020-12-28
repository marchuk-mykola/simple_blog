package com.marchuk.test.core.data.di

import com.marchuk.test.core.BuildConfig
import com.marchuk.test.core.data.api.PostsApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .build()

    @Provides
    @Singleton
    internal fun provideMoshi(): MoshiConverterFactory {
        val moshi = Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient, moshiConverterFactory: MoshiConverterFactory): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(moshiConverterFactory)
            .build()

    @Provides
    @Singleton
    internal fun provideApi(retrofit: Retrofit): PostsApi {
        return retrofit.create(PostsApi::class.java)
    }

}