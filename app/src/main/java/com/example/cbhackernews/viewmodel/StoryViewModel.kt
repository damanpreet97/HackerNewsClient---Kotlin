package com.example.cbhackernews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.cbhackernews.StoryRepository
import kotlinx.coroutines.Dispatchers

class StoryViewModel : ViewModel() {

    val storyRepository: StoryRepository = StoryRepository()
//
//    val topStoryList = liveData(Dispatchers.IO) {
//        val retrievedStories = storyRepository.getTopStoryList()
//        emit(retrievedStories)
//    }

    val topStoryDataList = liveData(Dispatchers.IO) {
        val retrievedStoriesDataList = storyRepository.getTopStoryList()
        emit(retrievedStoriesDataList)
    }


}