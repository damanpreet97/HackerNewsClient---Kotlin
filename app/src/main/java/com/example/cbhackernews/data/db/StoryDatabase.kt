package com.example.cbhackernews.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = arrayOf(TopStoryEntity::class, NewStoryEntity::class ,BestStoryEntity::class), version = 1, exportSchema = true)
abstract class StoryDatabase : RoomDatabase() {

    abstract fun getTopStoryDao() : TopStoryDao
    abstract fun getBestStoryDao() : BestStoryDao
    abstract fun getNewStoryDao() : NewStoryDao

    companion object{
        @Volatile private var instance: StoryDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            Log.e("TAG", "invoke database called")
            instance ?: buildDatabase(context).also{ instance = it}
        }

        private fun buildDatabase(context: Context) : StoryDatabase? {
            Log.e("TAG", "build database called")
            return Room.databaseBuilder(context.applicationContext, StoryDatabase::class.java, "story.db").fallbackToDestructiveMigration().build()
        }


    }
}