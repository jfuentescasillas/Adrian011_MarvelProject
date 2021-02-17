package com.example.adrian011_marvelproject.data

import com.example.adrian011_marvelproject.data.model.ResponseAllCharactersDataModel
import com.example.adrian011_marvelproject.data.network.MarvelNetwork
import retrofit2.http.GET

class MarvelRepository {
    suspend fun getAllCharacters(): ResponseAllCharactersDataModel {
        return MarvelNetwork().getAllCharacters()
    }
}