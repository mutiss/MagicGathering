package com.mutissx.magic.data.di

import android.app.Application
import com.mutissx.magic.data.BuildConfig
import com.mutissx.magic.data.remote.api.CardsApi
import com.mutissx.magic.data.repository.CardsRepositoryImpl
import com.mutissx.magic.domain.repository.CardsRepository
import java.util.concurrent.TimeUnit
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val CACHE_SIZE = 5 * 1024 * 1024L // 5 MB

val dataModule = module {
    single<CardsRepository> { CardsRepositoryImpl(get(), get()) }
    single<CardsApi> { get<Retrofit>().create(CardsApi::class.java) }

    single { provideRetrofit(get()) }
    single { provideOkHttpClient(androidApplication()) }
}

class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(2, TimeUnit.HOURS)
            .build()
        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL_SERVICE)
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

private fun provideOkHttpClient(application: Application): OkHttpClient {
    return OkHttpClient.Builder()
        .cache(Cache(directory = application.cacheDir, CACHE_SIZE))
        .addNetworkInterceptor(CacheInterceptor())
        .readTimeout(1, TimeUnit.MINUTES)
        .connectTimeout(1, TimeUnit.MINUTES)
        .retryOnConnectionFailure(true)
        .build()
}
