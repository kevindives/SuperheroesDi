package com.magicworld.superheroesdi.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SuperheroeItem(
    @SerializedName("alias")
    val alias: String = "",
    @SerializedName("altura")
    val altura: Double = 0.0,
    @SerializedName("city")
    val city: String = "",
    @SerializedName("facebook")
    val facebook: String = "",
    @SerializedName("lat")
    val lat: Double = 0.0,
    @SerializedName("lng")
    val lng: Double = 0.0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("occupation")
    val occupation: String = "",
    @SerializedName("powers")
    val powers: String = "",
    @SerializedName("urlPicture")
    val urlPicture: String = "",
    @SerializedName("zoom")
    val zoom: Float = 0.0f
): Serializable
