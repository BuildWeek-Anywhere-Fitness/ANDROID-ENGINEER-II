package com.example.anywherefitness.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class User (
    val username: String,
    val password: String,
    val email: String,
    val isInstructor: Boolean,
    val fitnessClassList: MutableList<FitnessClass>,

    @PrimaryKey(autoGenerate = false)
    val id: Int
)