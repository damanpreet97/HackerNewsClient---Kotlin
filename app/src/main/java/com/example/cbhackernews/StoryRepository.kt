package com.example.cbhackernews

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.cbhackernews.data.model.Story
import com.example.cbhackernews.network.RetrofitClient
import com.example.cbhackernews.network.StoryAPI
import com.example.cbhackernews.utils.NoConnectivityException
import retrofit2.create
import java.io.IOException
import kotlin.coroutines.coroutineContext

class StoryRepository {
    var storyDataMutableList : MutableLiveData<ArrayList<Story>> = MutableLiveData();
    var storyDataList : ArrayList<Story> = ArrayList()
    var storyList : ArrayList<Int> = ArrayList();

    var client: StoryAPI = RetrofitClient.getRetrofitInstance().create()

//    suspend fun getTopStoryList() = client.getTopStories()

    suspend fun getTopStoryList() : MutableLiveData<ArrayList<Story>> {

            storyList = client.getTopStories()
            for (i in 0..14) {
                Log.e("TAG", "id = " + storyList.get(i))
                storyDataList.add(client.getStory(storyList.get(i)))
            }
            storyDataMutableList.postValue(storyDataList)

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