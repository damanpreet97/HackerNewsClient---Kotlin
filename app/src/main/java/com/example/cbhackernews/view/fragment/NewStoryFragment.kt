package com.example.cbhackernews.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cbhackernews.R
import com.example.cbhackernews.data.db.NewStoryEntity
import com.example.cbhackernews.data.db.TopStoryEntity
import com.example.cbhackernews.data.model.Story
import com.example.cbhackernews.view.adapter.StoryAdapter
import com.example.cbhackernews.viewmodel.StoryViewModel
import kotlinx.android.synthetic.main.fragment_new_story.*
import kotlinx.android.synthetic.main.fragment_top_story.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewStoryFragment : Fragment() {
    companion object{
        private var instance : NewStoryFragment? = null
        fun getInstance():NewStoryFragment{
            if(instance == null)
                instance = NewStoryFragment()
            return instance as NewStoryFragment
        }
    }

    lateinit var storyViewModel : StoryViewModel
    var listStories : ArrayList<Story> = ArrayList()

    lateinit var storyAdapter: StoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_story, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar.show()
        recyclerViewStory.visibility = View.GONE
        storyAdapter = StoryAdapter(listStories)
        recyclerViewStory.adapter = storyAdapter
        recyclerViewStory.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        storyViewModel = ViewModelProvider(this).get(StoryViewModel::class.java)

//        storyViewModel.topStoryListFromDb.observe(this, Observer { showList(it.value!!) })
        CoroutineScope(Dispatchers.Main).launch { showList(storyViewModel.getNewStoryListFromDb()) }

        storyViewModel.newStoryDataList.observe(this, Observer{
            progressBar.show()
            updateList(it.value!!)
        })
    }

    private fun showList(list: List<NewStoryEntity>) {
        Log.e("TAG", "size of new list from db = ${list.size}" )
        if(list.size != 0) {
            recyclerViewStory.visibility = View.VISIBLE
            progressBar.hide()
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
        recyclerViewStory.visibility = View.VISIBLE
        progressBar.hide()
        listStories.clear()
        listStories.addAll(it)
        storyAdapter.notifyDataSetChanged()
        addNewStoriesToDb(listStories)
    }

    private fun addNewStoriesToDb(listStories: ArrayList<Story>) {
        val newStoryList = ArrayList<NewStoryEntity>()

        for(i in listStories){
            val newStoryEntity = NewStoryEntity(i.id, i.title, i.time, i.score)
            newStoryList.add(newStoryEntity)
        }
        GlobalScope.launch {
            storyViewModel.addNewStoriesToDb(newStoryList)
        }
    }

}