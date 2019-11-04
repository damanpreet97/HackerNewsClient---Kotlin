package com.example.cbhackernews.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.cbhackernews.data.model.Story
import kotlinx.coroutines.selects.select

@Dao
interface TopStoryDao{
    //this will insert & update according to whether entry is already present or not
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTopStories(storyList : List<TopStoryEntity>)

    @Query("select * from top_story_items")
    suspend fun getTopStoryList() : List<TopStoryEntity>
}