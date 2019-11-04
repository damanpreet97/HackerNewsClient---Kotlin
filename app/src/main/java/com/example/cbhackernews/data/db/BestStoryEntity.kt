package com.example.cbhackernews.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "best_story_items")
data class BestStoryEntity(

    @PrimaryKey(autoGenerate = false) val id: Int,
    val title: String,
    val score: Int,
    val time: Int

)