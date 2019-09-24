package com.example.anywherefitness.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class User (
    val username: String,
    val password: String,
    val instructor: Boolean = false,
    val token: String = "",

    @PrimaryKey(autoGenerate = false)
    val id: Int = 0
)

class CreateUser(
    val username: String,
    val password: String,
    val instructor: Boolean
)