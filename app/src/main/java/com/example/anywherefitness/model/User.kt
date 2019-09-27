package com.example.anywherefitness.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user_table")
class User (
    val username: String,
    val password: String,
    val instructor: Boolean = false,
    val token: String = "",
    val watchedWalkthrough: Boolean = false,

    @PrimaryKey(autoGenerate = false)
    val id: Int = 0
) : Serializable

class CreateUser(
    val username: String,
    val password: String,
    val instructor: Boolean
)