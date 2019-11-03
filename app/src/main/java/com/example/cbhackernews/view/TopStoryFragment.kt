package com.example.cbhackernews.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import androidx.lifecycle.ViewModelProviders
//import androidx.lifecycle.ViewModelProviders
import com.example.cbhackernews.R
import com.example.cbhackernews.model.Story
import com.example.cbhackernews.viewmodel.StoryViewModel
import kotlinx.android.synthetic.main.fragment_story.*

class TopStoryFragment : Fragment() {

    lateinit var storyViewModel : StoryViewModel
    var listStories : ArrayList<Story> = ArrayList()
//    private lateinit var layoutManager: RecyclerView.LayoutManager

    lateinit var storyAdapter: StoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_story, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.show()
        recyclerViewStory.visibility = GONE
        storyAdapter = StoryAdapter(listStories)
        recyclerViewStory.adapter = storyAdapter
        recyclerViewStory.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        storyViewModel = ViewModelProvider(this).get(StoryViewModel::class.java)
        storyViewModel.topStoryDataList.observe(this, Observer{
                updateList(it.value!!)
        })
    }

    private fun updateList(it: ArrayList<Story>) {
        Log.e("TAG", "array list size = "+it.size)
        recyclerViewStory.visibility = VISIBLE
        progressBar.hide()
        listStories.addAll(it)
        storyAdapter.notifyDataSetChanged()
    }


}