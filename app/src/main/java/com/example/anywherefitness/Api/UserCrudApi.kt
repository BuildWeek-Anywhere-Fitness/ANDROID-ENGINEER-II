package com.example.anywherefitness.Api

import com.example.anywherefitness.model.CreateUser
import com.example.anywherefitness.model.FitnessClass
import com.example.anywherefitness.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

//User
//username, password, id, isInstructor
interface UserCrudApi {
    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>

    @POST("users/register")
    fun addUser(@Body user: CreateUser): Call<CreateUser>

    @POST("users/login")
    fun getLoginToken(@Body user: User): Call<User>

    @GET("classes")
    fun getAllClasses(): Call<FitnessClass>

    @POST("classes/join/{id}")
    fun addUserToClass(@Path("id") id: Int): Call<FitnessClass>

    @GET("classes/client/{id}")
    fun getClientClass(@Path("id") id: Int): Call<FitnessClass>



}