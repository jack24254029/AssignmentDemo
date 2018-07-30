package com.github.ininmm.assignmentdemo.di

import com.github.ininmm.network.service.WeatherService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Michael Lien
 * on 2018/7/30.
 */
@Module
class ApiModule {
    companion object {
        private const val baseUrl = "https://www.cwb.gov.tw/"
        private const val dailyUrl = "https://tw.appledaily.com/index/dailyquote/"
        private const val BASEURL = "BASEURL"
    }

    @Provides
    @Named(BASEURL)
    internal fun provideBaseUrl(): String {
        return baseUrl
    }

    @Provides
    @Singleton
    internal fun provideHttpClient(): OkHttpClient {
        val newRequest = OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                val newRequest = chain!!.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .build()
                return@addInterceptor chain.proceed(newRequest)
            }
        }
        return newRequest.connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory() = SimpleXmlConverterFactory.create()

    @Provides
    @Singleton
    fun provideRxJavaAdapterFactory() = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(@Named(BASEURL) baseUrl: String,
                        converterFactory: Converter.Factory,
                        callAdapterFactory: CallAdapter.Factory,
                        client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(client)
                .build()
    }

    @Provides
    @Singleton
    fun provideWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }
}
