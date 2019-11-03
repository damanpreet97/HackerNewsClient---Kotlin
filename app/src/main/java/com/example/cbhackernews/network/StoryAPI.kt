package com.example.cbhackernews.network

import com.example.cbhackernews.model.Story
import com.example.cbhackernews.model.StoryListApiResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StoryAPI {

//    @GET("/topstories")
//    fun getTopStories(): Call<List<StoryListApiResponse>>

    @GET("/topstories")
    suspend fun getTopStories(): StoryListApiResponse

    @GET("/beststories")
    fun getBestStories(): Observable<List<Story>>

    @GET("/newstories")
    fun getNewStories(): Observable<List<Story>>

    @GET("/item/{id}.json")
    suspend fun getStory(@Path(value = "id") storyId: Int): Story
}