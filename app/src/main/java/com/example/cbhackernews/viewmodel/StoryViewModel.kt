package com.example.cbhackernews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.cbhackernews.MyApplication
import com.example.cbhackernews.data.StoryRepository
import com.example.cbhackernews.data.db.BestStoryEntity
import com.example.cbhackernews.data.db.NewStoryEntity
import com.example.cbhackernews.data.db.StoryDatabase
import com.example.cbhackernews.data.db.TopStoryEntity
import kotlinx.coroutines.Dispatchers

class StoryViewModel : ViewModel() {

     val storyDatabase : StoryDatabase = MyApplication.getDatabase()
     val storyRepository: StoryRepository = StoryRepository()

//    lateinit var newStoriesDb : List<NewStoryEntity>
//    lateinit var bestStoriesDb : List<BestStoryEntity>
//     var topStoriesDb : List<TopStoryEntity>? = null
//
//    suspend fun getTopStoryListFromDb() = topStoriesDb ? : {
//            storyDatabase.getTopStoryDao().getTopStoryList().also {
//                topStoriesDb = it
//            }
//    }


    suspend fun getTopStoryListFromDb() : List<TopStoryEntity>{
        return storyDatabase.getTopStoryDao().getTopStoryList()
    }

    fun addTopStoriesToDb(topStoryList:  List<TopStoryEntity>) {
        storyDatabase.getTopStoryDao().insertAllTopStories(topStoryList)
    }

    val topStoryDataList = liveData(Dispatchers.IO) {
        val retrievedStoriesDataList = storyRepository.getTopStoryList()
        emit(retrievedStoriesDataList)
    }

    suspend fun getNewStoryListFromDb() : List<NewStoryEntity>{
        return storyDatabase.getNewStoryDao().getNewStoryList()
    }

    fun addNewStoriesToDb(newStoryList:  List<NewStoryEntity>) {
        storyDatabase.getNewStoryDao().insertAllNewSTories(newStoryList)
    }

    val newStoryDataList = liveData(Dispatchers.IO) {
        val retrievedStoriesDataList = storyRepository.getNewStoryList()
        emit(retrievedStoriesDataList)
    }

    suspend fun getBestStoryListFromDb() : List<BestStoryEntity>{
        return storyDatabase.getBestStoryDao().getBestStoryList()
    }

    fun addBestStoriesToDb(bestStoryList:  List<BestStoryEntity>) {
        storyDatabase.getBestStoryDao().insertAllBestStories(bestStoryList)
    }

    val bestStoryDataList = liveData(Dispatchers.IO) {
        val retrievedStoriesDataList = storyRepository.getBestStoryList()
        emit(retrievedStoriesDataList)
    }


}