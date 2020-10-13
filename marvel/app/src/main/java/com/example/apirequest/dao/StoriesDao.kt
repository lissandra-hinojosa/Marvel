package com.example.apirequest.dao

import com.example.apirequest.models.story.Story

class StoriesDao private constructor(val status: Status, val stories: List<Story>?, val error: String?){
    companion object {
        fun success(stories: List<Story>?) = StoriesDao(Status.SUCCESS, stories, null)
        fun error(error: String) = StoriesDao(Status.ERROR, null, error)
    }
}