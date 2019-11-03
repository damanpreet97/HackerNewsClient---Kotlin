package com.example.cbhackernews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cbhackernews.model.Story
import com.example.cbhackernews.model.StoryListApiResponse
import com.example.cbhackernews.network.RetrofitClient
import com.example.cbhackernews.network.StoryAPI
import io.reactivex.Scheduler
import retrofit2.Callback
import retrofit2.create

class StoryRepository {
    var storyDataMutableList : MutableLiveData<ArrayList<Story>> = MutableLiveData();
    var storyDataList : ArrayList<Story> = ArrayList()
    var storyList : MutableLiveData<ArrayList<Int>> = MutableLiveData();

    var client: StoryAPI = RetrofitClient.getRetrofitInstance().create()

//    suspend fun getTopStoryList() = client.getTopStories()

    suspend fun getTopStoryList() : MutableLiveData<ArrayList<Story>> {

        for (id in client.getTopStories().listStoryId)
            storyDataList.add(client.getStory(id))

         storyDataMutableList.value = storyDataList

        return storyDataMutableList
    }


//    fun getTopStoryList(): MutableLiveData<StoryListApiResponse> {
//        val liveData = MutableLiveData<StoryListApiResponse>()
//
//        client.getTopStories().enqueue(object : Callback<StoryListApiResponse>{
//
//        })

//        client.getTodo(id).enqueue(object: Callback<Todo>{
//            override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
//                if (response.isSuccessful) {
//
//                    // When data is available, populate LiveData
//                    liveData.value = response.body()
//                }
//            }
//
//            override fun onFailure(call: Call<Todo>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })

        // Synchronously return LiveData
        // Its value will be available onResponse
//        return liveData
//    }
}