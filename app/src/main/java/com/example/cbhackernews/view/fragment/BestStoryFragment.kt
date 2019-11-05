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
import com.example.cbhackernews.data.db.BestStoryEntity
import com.example.cbhackernews.data.model.Story
import com.example.cbhackernews.view.adapter.StoryAdapter
import com.example.cbhackernews.viewmodel.StoryViewModel
import kotlinx.android.synthetic.main.fragment_best_story.*
import kotlinx.android.synthetic.main.fragment_top_story.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BestStoryFragment : Fragment() {

    companion object{
        private var instance : BestStoryFragment? = null
        fun getInstance():BestStoryFragment{
            if(instance == null)
                instance = BestStoryFragment()
            return instance as BestStoryFragment
        }
    }
    lateinit var storyViewModel : StoryViewModel
    var listStories : ArrayList<Story> = ArrayList()

    lateinit var storyAdapter: StoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_best_story, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar2.show()
        recyclerViewBestStory.visibility = View.GONE
        storyAdapter = StoryAdapter(listStories)
        recyclerViewBestStory.adapter = storyAdapter
        recyclerViewBestStory.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        storyViewModel = ViewModelProvider(this).get(StoryViewModel::class.java)

        CoroutineScope(Dispatchers.Main).launch { showList(storyViewModel.getBestStoryListFromDb()) }

        storyViewModel.bestStoryDataList.observe(this, Observer{
            progressBar2.show()
            updateList(it.value!!)
        })
    }

    private fun showList(list: List<BestStoryEntity>) {
        Log.e("TAG", "size of best list from db = ${list.size}" )
        if(list.size != 0) {
            recyclerViewBestStory.visibility = View.VISIBLE
            progressBar2.hide()
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
        recyclerViewBestStory.visibility = View.VISIBLE
        progressBar2.hide()
        listStories.clear()
        listStories.addAll(it)
        storyAdapter.notifyDataSetChanged()
        addBestStoriesToDb(listStories)
    }

    private fun addBestStoriesToDb(listStories: ArrayList<Story>) {
        val bestStoryList = ArrayList<BestStoryEntity>()

        for(i in listStories){
            val bestStoryEntity = BestStoryEntity(i.id, i.title, i.time, i.score)
            bestStoryList.add(bestStoryEntity)
        }
        GlobalScope.launch {
            storyViewModel.addBestStoriesToDb(bestStoryList)
        }
    }


}