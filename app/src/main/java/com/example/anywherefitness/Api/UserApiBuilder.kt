package com.example.anywherefitness.Api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object UserApiBuilder {
    private const val BASE_URL = ""
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    fun userRetro(): UserGetApi {
        return retrofit.create(UserGetApi::class.java)
    }
}