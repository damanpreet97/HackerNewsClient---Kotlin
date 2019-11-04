package com.example.cbhackernews.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.cbhackernews.MyApplication
import com.example.cbhackernews.MyApplication.Companion.storyDatabase
import com.example.cbhackernews.StoryRepository
import com.example.cbhackernews.data.db.StoryDatabase
import com.example.cbhackernews.data.db.TopStoryEntity
import com.example.cbhackernews.data.model.Story
import kotlinx.coroutines.Dispatchers

class StoryViewModel : ViewModel() {

     val storyDatabase : StoryDatabase = MyApplication.getDatabase()
//    MyAppplcation.storyDatabase.getTopStoryDao().getTopStoryList().observe(this)
    val storyRepository: StoryRepository = StoryRepository()
//
//    val topStoryList = liveData(Dispatchers.IO) {
//        val retrievedStories = storyRepository.getTopStoryList()
//        emit(retrievedStories)
//    }

//    val topStoryListFromDb = liveData(Dispatchers.IO){
//        emit(storyDatabase.getTopStoryDao().getTopStoryList())
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


}