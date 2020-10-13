package com.example.apirequest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apirequest.R
import com.example.apirequest.databinding.ItemStoriesBinding
import com.example.apirequest.models.story.Story
import kotlinx.android.synthetic.main.item_stories.view.*

class StoriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var stories = arrayListOf<Story>()
    private val maxTitleSize = 20

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StoriesViewHolder(ItemStoriesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
//        return StoriesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_stories, parent, false))
    }

    override fun getItemCount(): Int {
        return stories.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as StoriesViewHolder
        holder.bind(stories[position])
    }

    inner class StoriesViewHolder(val binding: ItemStoriesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story) {
            binding.story = story
            binding.executePendingBindings()
        }
    }

    fun setCharacterStories(storiesList: List<Story>){
        stories.clear()
        stories.addAll(storiesList)
        notifyDataSetChanged()
    }
}