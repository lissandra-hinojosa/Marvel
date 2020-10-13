package com.example.apirequest.models.series

import java.io.Serializable

data class SeriesData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Series>
): Serializable