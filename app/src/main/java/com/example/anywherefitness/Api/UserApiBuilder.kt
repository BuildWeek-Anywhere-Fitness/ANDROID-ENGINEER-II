package com.example.anywherefitness.Api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object UserApiBuilder {
    private const val BASE_URL = "https://anywhere-health.herokuapp.com/api/"
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    fun userRetro(): UserCrudApi {
        return retrofit.create(UserCrudApi::class.java)
    }
}