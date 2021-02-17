package com.example.adrian011_marvelproject.data.network

import com.example.adrian011_marvelproject.data.model.ResponseAllCharactersDataModel
import retrofit2.http.GET


interface MarvelService {
    @GET("v1/public/characters")
    suspend fun getAllCharacters(): ResponseAllCharactersDataModel
}