package com.example.anywherefitness.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "class_table")
class FitnessClass (
    val name: String,
    val type: String,
    val startTime: String,
    val duration: Int,
    val intensity: Int,
    val location: String,
    val instructor_id: Int,
    val instructor: Boolean,
    val numberOfAttendees: Int,
    val classSize: Int,

    @PrimaryKey(autoGenerate = false)
    val id: Int = 0
)