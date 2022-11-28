package com.magicworld.superheroesdi.data

import com.magicworld.superheroesdi.model.Superheroe
import retrofit2.http.GET

interface ApiService {
    @GET("kevindives/Practicandomovil/superheroes")
    suspend fun getSuperheroes(): Superheroe
}