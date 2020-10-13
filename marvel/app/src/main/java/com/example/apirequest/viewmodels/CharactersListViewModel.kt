package com.example.apirequest.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apirequest.dao.CharactersDao
import com.example.apirequest.models.character.Character
import com.example.apirequest.models.character.CharacterResponse
import com.example.apirequest.repositories.MarvelRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersListViewModel(private val marvelRepository: MarvelRepository) : ViewModel() {

    var charactersResult = MutableLiveData<CharactersDao>()
        var characters = SingleLiveEvent<List<Character>>()
    val charactersList = mutableListOf<Character>()
    var newCharactersList= arrayListOf<Character>()
    var page = 0
    var total = 20
    var successRequest = false
//    private val
//    private val characters = arrayListOf<Character>()


    fun getCharacters(): Boolean {
            marvelRepository.getCharacters(page*total,total)
                .enqueue(object : Callback<CharacterResponse> {
                    override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                        charactersResult.value = CharactersDao.error(t.localizedMessage?:"Error Character") //TODO Use string resource
                        successRequest = false
                    }

                    override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>) {
                        if(response.isSuccessful) {
                            page++
//                            charactersResult.value = CharactersDao.success(response.body()!!)
//                            val list = characters.value ?: mutableListOf()
//                            list.addAll(response.body()!!.data.results)
                            //characters.value.addAll(response.body()!!.data.results)
                            characters.value = response.body()!!.data.results
                            charactersList.addAll(response.body()!!.data.results)
                            successRequest = true
                        }
                    }
                })
            return successRequest
    }

//    private fun addCharactes(newCharacters: List<Character>){
//        kotlin.repeat(newCharacters.size){
//            newCharactersList.add(newCharacters[it])
//        }
//        charactersResult.value = CharactersDao.success(newCharactersList)
//    }

//    private fun validateLimit(limit: Int): Boolean{
//        return when {
//            limit == 0 -> false
//            limit <= 0 || limit > 100 -> false
//            else -> true
//        }
//    }

}