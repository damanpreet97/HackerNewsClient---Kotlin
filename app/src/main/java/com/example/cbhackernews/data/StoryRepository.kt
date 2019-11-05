package com.example.cbhackernews.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cbhackernews.data.model.Story
import com.example.cbhackernews.network.RetrofitClient
import com.example.cbhackernews.network.StoryAPI
import retrofit2.create

class StoryRepository {
    var topStoryDataMutableList : MutableLiveData<ArrayList<Story>> = MutableLiveData();
    var topStoryDataList : ArrayList<Story> = ArrayList()
    var topStoryList : ArrayList<Int> = ArrayList();

    var newStoryDataMutableList : MutableLiveData<ArrayList<Story>> = MutableLiveData();
    var newStoryDataList : ArrayList<Story> = ArrayList()
    var newStoryList : ArrayList<Int> = ArrayList();

    var bestStoryDataMutableList : MutableLiveData<ArrayList<Story>> = MutableLiveData();
    var bestStoryDataList : ArrayList<Story> = ArrayList()
    var bestStoryList : ArrayList<Int> = ArrayList();

    var client: StoryAPI = RetrofitClient.getRetrofitInstance().create()

//    suspend fun getTopStoryList() = client.getTopStories()

    suspend fun getTopStoryList() : MutableLiveData<ArrayList<Story>> {

            topStoryList = client.getTopStories()
            for (i in 0..14) {
                Log.e("TAG", "id = " + topStoryList.get(i))
                topStoryDataList.add(client.getStory(topStoryList.get(i)))
            }
            topStoryDataMutableList.postValue(topStoryDataList)

            return topStoryDataMutableList

    }
    suspend fun getNewStoryList() : MutableLiveData<ArrayList<Story>> {

            newStoryList = client.getNewStories()
            for (i in 0..15) {
                Log.e("TAG", "id = " + newStoryList.get(i))
                newStoryDataList.add(client.getStory(newStoryList.get(i)))
            }
            newStoryDataMutableList.postValue(newStoryDataList)

            return newStoryDataMutableList

    }
    suspend fun getBestStoryList() : MutableLiveData<ArrayList<Story>> {

            bestStoryList = client.getBestStories()
            for (i in 0..16) {
                Log.e("TAG", "id = " + bestStoryList.get(i))
                bestStoryDataList.add(client.getStory(bestStoryList.get(i)))
            }
            bestStoryDataMutableList.postValue(bestStoryDataList)

            return bestStoryDataMutableList

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