package com.example.apirequest.dao

import com.example.apirequest.models.character.Character
import com.example.apirequest.models.character.CharacterResponse

//Helps us use a single variable or element on a callback instead of two ( for success or failure)
//Private constructor lets just the constructor to use the initial parameters
class CharactersDao private constructor(val status: Status, val characterResponse: CharacterResponse?, val error: String?) {

    companion object {
        fun success(characterResponse: CharacterResponse) = CharactersDao(Status.SUCCESS, characterResponse, null)
        fun error(error: String) = CharactersDao(Status.ERROR, null, error)

    }
}