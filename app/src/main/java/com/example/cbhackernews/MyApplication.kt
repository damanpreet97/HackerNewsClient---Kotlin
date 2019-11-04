package com.example.cbhackernews

import android.app.Application
import android.content.Context
import com.example.cbhackernews.data.db.StoryDatabase

//class MyApplication : Application(){
//
////    lateinit var storyDatabase : StoryDatabase
//
//    override fun onCreate() {
//        super.onCreate()
//        // initialize for any
//
//        // Use ApplicationContext.
//        // example: SharedPreferences etc...
//        instance = this
//    }
//    companion object {
////        private var instance: MyApplication? = null
//        lateinit var instance: MyApplication
//            private set
//
//        fun applicationContext() : Context {
//            return instance.applicationContext
//        }
//
//        val storyDatabase = StoryDatabase.invoke(context = applicationContext())!!
//        fun getDatabase() : StoryDatabase{
//            return storyDatabase
//        }
//    }
//
//}

class MyApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MyApplication? = null

        fun applicationContext() : MyApplication {
            return instance as MyApplication
        }

//        val storyDatabase = StoryDatabase.invoke(context = applicationContext())!!
        var storyDatabase : StoryDatabase? = null
        fun getDatabase() : StoryDatabase{
            if(storyDatabase == null)
            storyDatabase = StoryDatabase.invoke(context = applicationContext())!!
            return storyDatabase as StoryDatabase
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}