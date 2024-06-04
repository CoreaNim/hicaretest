package net.hicare.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.hicare.core.const.Const
import net.hicare.core.network.HicareService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    @Named("HICARE_URL")
    fun provideHicareUrl() = Const.HICARE_SERVER_URL

    @Singleton
    @Provides
    fun provideHicareService(
        @Named("HICARE_URL") baseUrl: String,
        okHttpClient: OkHttpClient
    ): HicareService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HicareService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

}