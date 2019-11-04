package com.example.cbhackernews.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cbhackernews.R
import com.example.cbhackernews.data.model.Story
import java.text.SimpleDateFormat


    class StoryViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_story, parent, false)) {
        private var mTitleView: TextView? = null
        private var mTime: TextView? = null


        init {
            mTitleView = itemView.findViewById(R.id.story_text)
            mTime = itemView.findViewById(R.id.story_time)
        }

        fun bind(story: Story) {
            mTitleView?.text = story.title

            val date = story.time
            val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
            val formatedDate = formatter.format(date)

            mTime?.text = formatedDate
        }

    }

