package com.example.apirequest.services

import retrofit2.Retrofit

class MarvelService(retrofit: Retrofit) {

    private val service = retrofit.create(MarvelApi::class.java)
    private val ts = 1
    private val apikey = "f5e3f96036a9823cf29c52c5ab96d0a2"
    private val hash = "cd826362b1990e4a8fef9737f0cbd343"

    fun getCharacters(offset: Int, limit: Int) = service.getCharacters(offset,limit,ts, apikey, hash)

    fun getComics(characterId: Int) = service.getComics(characterId,ts,apikey,hash)
    fun getEvents(characterId: Int) = service.getEvents(characterId,ts,apikey,hash)
    fun getSeries(characterId: Int) = service.getSeries(characterId,ts,apikey,hash)
    fun getStories(characterId: Int) = service.getStories(characterId,ts,apikey,hash)

}