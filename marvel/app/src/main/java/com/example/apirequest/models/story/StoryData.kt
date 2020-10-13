package com.example.apirequest.models.story

import java.io.Serializable

data class StoryData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Story>
): Serializable