package com.github.ininmm.network

import com.github.ininmm.network.service.WeatherService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Maybe
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

/**
 * Created by Michael Lien
 * on 2018/7/25.
 */
object ApiUtils {
    private var retrofit: Retrofit? = null

    val testAPI: WeatherService by lazy { getRetrofit().create(WeatherService::class.java) }
    private const val baseUrl = "https://www.cwb.gov.tw/"
    private const val dailyUrl = "https://tw.appledaily.com/index/dailyquote/"
    private val gson: Gson by lazy { GsonBuilder().excludeFieldsWithoutExposeAnnotation().create() }

    /**
     * 取得 Retrofit Client
     */
    @JvmStatic
    fun getRetrofit(): Retrofit {
        return retrofit ?: synchronized(this) {
            retrofit ?: buildAPI().also { retrofit = it }
        }
    }

    /**
     * 返回 Jsoup document
     */
    fun getJsoup(url: String = dailyUrl): Flowable<Document> {
        return Flowable.create<Document> ({
            val document = Jsoup.connect(url).get()
            it.onNext(document)
            it.onComplete()
        }, BackpressureStrategy.BUFFER)
    }

    /**
     * 建造 Service 的工廠方法
     */
    fun<T> createService(serviceClass: Class<T>): T {
        return getRetrofit().create(serviceClass)
    }

    private fun buildAPI(factory: Converter.Factory = SimpleXmlConverterFactory.create()): Retrofit {
        val client = createClient()

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    private fun createClient(): OkHttpClient {
        val builder = OkHttpClient.Builder().apply {
            addInterceptor { chain: Interceptor.Chain? ->
                val newRequest = chain!!.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .build()
                return@addInterceptor chain.proceed(newRequest)
            }
        }

        return builder.connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build()
    }

    /**
     * 使用正則將字串依照標點符號切割
     * @param rawString 要切割的字串
     * @return 切好的 Array<String>
     */
    private fun filterString(rawString: String): Array<String> {
        val regEx = "\\.|：|。|！|；"
        val pattern = Pattern.compile(regEx)
        val matcher = pattern.matcher(rawString)
        val array = pattern.split(rawString)
        if (array.isNotEmpty()) {
            var count = 0
            while (count < array.size) {
                if (matcher.find()) {
                    array[count] += matcher.group()
                }
                count++
            }
        }
        return array
    }
}