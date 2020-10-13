package com.example.apirequest.services

import com.example.apirequest.models.character.CharacterResponse
import com.example.apirequest.models.comic.ComicResponse
import com.example.apirequest.models.event.EventResponse
import com.example.apirequest.models.series.SeriesResponse
import com.example.apirequest.models.story.StoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("v1/public/characters")
    fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("ts") ts: Int,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Call<CharacterResponse>

    @GET("v1/public/characters/{type}/comics")
    fun getComics(
        @Path("type") charId: Int,
        @Query("ts") ts: Int,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Call<ComicResponse>

    @GET("v1/public/characters/{type}/events")
    fun getEvents(
        @Path("type") charId: Int,
        @Query("ts") ts: Int,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Call<EventResponse>

    @GET("v1/public/characters/{type}/series")
    fun getSeries(
        @Path("type") charId: Int,
        @Query("ts") ts: Int,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Call<SeriesResponse>

    @GET("v1/public/characters/{type}/stories")
    fun getStories(
        @Path("type") charId: Int,
        @Query("ts") ts: Int,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Call<StoryResponse>
}