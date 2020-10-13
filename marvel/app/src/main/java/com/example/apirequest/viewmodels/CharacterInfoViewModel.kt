package com.example.apirequest.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apirequest.dao.ComicsDao
import com.example.apirequest.dao.EventsDao
import com.example.apirequest.dao.SeriesDao
import com.example.apirequest.dao.StoriesDao
import com.example.apirequest.models.comic.ComicResponse
import com.example.apirequest.models.event.EventResponse
import com.example.apirequest.models.series.SeriesResponse
import com.example.apirequest.models.story.StoryResponse
import com.example.apirequest.repositories.MarvelRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CharacterInfoViewModel(private val marvelRepository: MarvelRepository) : ViewModel() {

    val comicsResult = MutableLiveData<ComicsDao>()
    val eventsResult = MutableLiveData<EventsDao>()
    val seriesResult = MutableLiveData<SeriesDao>()
    val storiesResult = MutableLiveData<StoriesDao>()

    //    private val
    fun getComics(characterId: Int){
        marvelRepository.getComics(characterId)
            .enqueue(object : Callback<ComicResponse> {
                override fun onFailure(call: Call<ComicResponse>, t: Throwable) {
                    comicsResult.value = ComicsDao.error(t.localizedMessage?:"")

                }
                override fun onResponse(call: Call<ComicResponse>, response: Response<ComicResponse>) {
                    if (response.isSuccessful)
                        comicsResult.value = ComicsDao.success(response.body()!!.data.results)
                }
            })
    }

    fun getEvents(characterId: Int) {
        marvelRepository.getEvents(characterId)
            .enqueue( object : Callback<EventResponse>{
                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                    eventsResult.value = EventsDao.error(t.localizedMessage)
                }
                override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                    eventsResult.value = EventsDao.success(response.body()!!.data.results)
                }
            })
    }

    fun getSeries(characterId: Int) {
        marvelRepository.getSeries(characterId)
            .enqueue( object : Callback<SeriesResponse>{
                override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {
                    seriesResult.value = SeriesDao.error(t.localizedMessage ?: "Error")
                }
                override fun onResponse(call: Call<SeriesResponse>, response: Response<SeriesResponse>) {
                    seriesResult.value = SeriesDao.success(response.body()!!.data.results)
                }

            })
    }

    fun getStories(characterId: Int) {
        marvelRepository.getStories(characterId)
            .enqueue( object : Callback<StoryResponse>{
                override fun onFailure(call: Call<StoryResponse>, t: Throwable) {
                    storiesResult.value = StoriesDao.error(t.localizedMessage)
                }
                override fun onResponse(call: Call<StoryResponse>, response: Response<StoryResponse>) {
                    storiesResult.value = StoriesDao.success(response.body()!!.data.results)
                }
            })
    }
}