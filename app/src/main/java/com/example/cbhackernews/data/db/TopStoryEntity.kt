package com.example.cbhackernews.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "top_story_items")
data class TopStoryEntity(

    @PrimaryKey(autoGenerate = false) val id: Int,
    val title: String,
    val score: Int,
    val time: Int

)