package com.example.apirequest.dao

import com.example.apirequest.models.series.Series

class SeriesDao private constructor(val status: Status, val series: List<Series>?, val error: String?){

    companion object {
        fun success(series: List<Series>) = SeriesDao(Status.SUCCESS, series, null)
        fun error(error: String) = SeriesDao(Status.ERROR, null, error)
    }
}