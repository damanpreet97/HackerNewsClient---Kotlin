package com.example.cbhackernews.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [TopStoryEntity::class, NewStoryEntity::class ,BestStoryEntity::class], version = 1)
abstract class StoryDatabase : RoomDatabase() {

    abstract fun getTopStoryDao() : TopStoryDao
    abstract fun getBestStoryDao() : BestStoryDao
    abstract fun getNewStoryDao() : NewStoryDao

    companion object{
        @Volatile private var instance: StoryDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: builDatabase(context).also{ instance = it}
        }

        private fun builDatabase(context: Context) : StoryDatabase? {
            return Room.databaseBuilder(context.applicationContext, StoryDatabase::class.java, "story.db").build()
        }


    }
}