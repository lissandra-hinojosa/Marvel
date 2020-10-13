package com.example.apirequest.dao

import com.example.apirequest.models.comic.Comic

class ComicsDao private constructor(val status: Status, val comics: List<Comic>?, val error: String?) {
    companion object {
        fun success(comics: List<Comic>) = ComicsDao(Status.SUCCESS, comics, null)
        fun error(error: String) = ComicsDao(Status.ERROR, null, error)
    }
}