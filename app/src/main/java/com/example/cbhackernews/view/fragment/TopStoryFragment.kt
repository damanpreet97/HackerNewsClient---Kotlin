package com.example.cbhackernews.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import androidx.lifecycle.ViewModelProviders
//import androidx.lifecycle.ViewModelProviders
import com.example.cbhackernews.R
import com.example.cbhackernews.data.db.TopStoryEntity
import com.example.cbhackernews.data.model.Story
import com.example.cbhackernews.view.adapter.StoryAdapter
import com.example.cbhackernews.viewmodel.StoryViewModel
import kotlinx.android.synthetic.main.fragment_new_story.*
import kotlinx.android.synthetic.main.fragment_top_story.*
import kotlinx.coroutines.*

class TopStoryFragment : Fragment() {

    companion object{
        private var instance : TopStoryFragment? = null
        fun getInstance():TopStoryFragment{
            if(instance == null)
                instance = TopStoryFragment()
            return instance as TopStoryFragment
        }
    }

    lateinit var storyViewModel : StoryViewModel
    var listStories : ArrayList<Story> = ArrayList()
//    private lateinit var layoutManager: RecyclerView.LayoutManager
//    lateinit var storyDatabase: StoryDatabase


    lateinit var storyAdapter: StoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_top_story, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar1.show()
        recyclerViewTopStory.visibility = GONE
        storyAdapter = StoryAdapter(listStories)
        recyclerViewTopStory.adapter = storyAdapter
        recyclerViewTopStory.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        storyViewModel = ViewModelProvider(this).get(StoryViewModel::class.java)

//        storyViewModel.topStoryListFromDb.observe(this, Observer { showList(it.value!!) })
        CoroutineScope(Dispatchers.Main).launch { showList(storyViewModel.getTopStoryListFromDb()) }

        storyViewModel.topStoryDataList.observe(this, Observer{
            progressBar1.show()
            updateList(it.value!!)
        })
    }

    private fun showList(list: List<TopStoryEntity>) {
        Log.e("TAG", "size of Top list from db = ${list.size}" )
        if(list.size != 0) {
            recyclerViewTopStory.visibility = VISIBLE
            progressBar1.hide()
            listStories.clear()
            for(i in list){
                val story = Story(i.id, i.title, i.time, i.score)
                listStories.add(story)
            }
            storyAdapter.notifyDataSetChanged()
        }
    }


    private fun updateList(it: ArrayList<Story>) {
        Log.e("TAG", "array list size = "+it.size)
        recyclerViewTopStory.visibility = VISIBLE
        progressBar1.hide()
        listStories.clear()
        listStories.addAll(it)
        storyAdapter.notifyDataSetChanged()
        addTopStoriesToDb(listStories)
    }

    private fun addTopStoriesToDb(listStories: ArrayList<Story>) {
        val topStoryList = ArrayList<TopStoryEntity>()

        for(i in listStories){
            val topStoryEntity = TopStoryEntity(i.id, i.title, i.time, i.score)
            topStoryList.add(topStoryEntity)
        }
        GlobalScope.launch {
            storyViewModel.addTopStoriesToDb(topStoryList)
        }
    }


}