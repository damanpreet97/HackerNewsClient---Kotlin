package com.example.cbhackernews.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cbhackernews.data.model.Story


@Dao
interface BestStoryDao{
//    this will insert & update according to whether entry is already present or not
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBestStories(storyList : List<BestStoryEntity>)

    @Query("select * from best_story_items")
    suspend fun getBestStoryList() : List<BestStoryEntity>
}