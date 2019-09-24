package com.example.anywherefitness.Api

import com.example.anywherefitness.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
//User
//username, password, id, isInstructor
interface UserCrudApi {
    @GET("users")
    fun getUser(): Call<List<User>>

    @POST("users/register")
    fun addUser(@Body user: User): Call<User>
}