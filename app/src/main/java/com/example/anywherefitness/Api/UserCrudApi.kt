package com.example.anywherefitness.Api

import com.example.anywherefitness.model.CreateUser
import com.example.anywherefitness.model.FitnessClass
import com.example.anywherefitness.model.FitnessClassResult
import com.example.anywherefitness.model.User
import com.example.anywherefitness.ui.Instructor.CreateFitnessClass
import retrofit2.Call
import retrofit2.http.*

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
    fun getAllClasses(): Call<List<FitnessClass>>

    @POST("classes/join/{id}")
    fun addUserToClass(@Path("id") id: Int,
                       @Body userId: Int): Call<FitnessClass>

    @GET("classes/client/{id}")
    fun getClientClass(@Path("id") id: Int): Call<FitnessClass>

    @POST("classes")
    fun addClass(@Body createFitnessClass: CreateFitnessClass): Call<CreateFitnessClass>

    @DELETE("classes/{id}")
    fun deleteClass(@Path("id") classIdPath: Int): Call<Void>



}