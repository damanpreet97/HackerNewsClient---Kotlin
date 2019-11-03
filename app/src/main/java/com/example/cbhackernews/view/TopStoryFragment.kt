package com.example.cbhackernews.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cbhackernews.R
import com.example.cbhackernews.model.Story
import com.example.cbhackernews.viewmodel.StoryViewModel

class TopStoryFragment : Fragment() {

    lateinit var storyViewModel : StoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_story, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        storyViewModel = ViewModelProvider(this).get(StoryViewModel::class.java)
        storyViewModel.topStoryDataList.observe(this, Observer<ArrayList<Story>>{
                updateList(it)
        })
    }

    private fun updateList(it: ArrayList<Story>) {
        Log.e("Tag", "array list size = "+it.size)
    }


}