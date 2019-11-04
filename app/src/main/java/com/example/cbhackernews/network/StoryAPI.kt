package com.example.cbhackernews.network

import com.example.cbhackernews.data.model.Story
import com.example.cbhackernews.utils.Constants
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface StoryAPI {

//    @GET("/topstories")
//    fun getTopStories(): Call<List<StoryListApiResponse>>

    @GET("topstories.json")
    suspend fun getTopStories(): ArrayList<Int>

    @GET("beststories")
    fun getBestStories(): Observable<List<Story>>

    @GET("newstories")
    fun getNewStories(): Observable<List<Story>>

    @GET("item/{id}.json")
    suspend fun getStory(@Path(value = "id") storyId: Int): Story

//    companion object {
//        operator fun invoke(
//            connectivityInterceptor: ConnectivityInterceptor
//        ): StoryAPI {
//
//            val requestInterceptor = Interceptor { chain ->
//
//
//                val url = chain.request()
//                    .url()
//                    .newBuilder()
//                    .build()
//                val request = chain.request()
//                    .newBuilder()
//                    .url(url)
//                    .build()
//                return@Interceptor chain.proceed(request)
//            }
//            val okHttpClient = OkHttpClient.Builder()
//                .addInterceptor(requestInterceptor)
//                .addInterceptor(connectivityInterceptor)
//                .build()
//
//
//            return Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl(Constants.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
//                .build().create(StoryAPI::class.java)
//        }
//    }
}