package com.example.movies.trends.data.api

import com.example.movies.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitProvidersDI {
    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit =
        Retrofit.Builder().baseUrl(
            BASE_URL,
        ).addConverterFactory(MoshiConverterFactory.create())
            .client(provideOkhttpClient()).build()

    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient = OkHttpClient.Builder().addInterceptor(MovieInterceptor).build()

    @Singleton
    @Provides
    fun provideNewReleasesClientApi(retrofit: Retrofit): NewReleasesService = retrofit.create(NewReleasesService::class.java)

    @Singleton
    @Provides
    fun provideTopRatedClientApi(retrofit: Retrofit): TopRatedService = retrofit.create(TopRatedService::class.java)
}
