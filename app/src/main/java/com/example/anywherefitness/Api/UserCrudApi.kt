package com.example.anywherefitness.Api

import com.example.anywherefitness.model.CreateUser
import com.example.anywherefitness.model.FitnessClass
import com.example.anywherefitness.model.User
import com.example.anywherefitness.viewmodel.CreateFitnessClass
import retrofit2.Call
import retrofit2.http.*

interface UserCrudApi {

    @GET("users/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>

    @POST("users/register")
    fun addUser(@Body user: CreateUser): Call<CreateUser>

    @POST("users/login")
    fun getLoginToken(@Body user: User): Call<User>

    @GET("classes")
    fun getAllClasses(@Header ("Authorization") token: String): Call<List<FitnessClass>>

    //These functions are unused because the API does not support clients registering for classes
    @POST("classes/join/{id}")
    fun addUserToClass(@Path("id") id: Int,
                       @Body userId: Int): Call<FitnessClass>

    @GET("classes/client/{id}")
    fun getClientClass(@Path("id") id: Int): Call<FitnessClass>

    @POST("classes")
    fun addClass(@Header ("Authorization") token: String, @Body createFitnessClass: CreateFitnessClass): Call<Void>

    @DELETE("classes/{id}")
    fun deleteClass(@Header ("Authorization") token: String, @Path("id") classIdPath: Int): Call<Void>



}