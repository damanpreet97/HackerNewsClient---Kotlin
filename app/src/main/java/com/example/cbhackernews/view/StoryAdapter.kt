package com.example.cbhackernews.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cbhackernews.data.model.Story
import java.util.*

class StoryAdapter(private val list: ArrayList<Story>)
    : RecyclerView.Adapter<StoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StoryViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story: Story = list.get(position)
        holder.bind(story)
    }

    override fun getItemCount(): Int = list.size

}
