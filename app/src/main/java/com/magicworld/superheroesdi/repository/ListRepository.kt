package com.magicworld.superheroesdi.repository

import com.magicworld.superheroesdi.data.ApiService
import javax.inject.Inject

class ListRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getSuperheroes() = apiService.getSuperheroes()
}