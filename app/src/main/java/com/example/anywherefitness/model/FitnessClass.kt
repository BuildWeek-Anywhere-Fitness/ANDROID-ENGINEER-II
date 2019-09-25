package com.example.anywherefitness.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "class_table")
class FitnessClass (
    val name: String = "",
    val type: String = "",
    val starttime: String = "",
    val duration: String = "",
    val intensity: String = "",
    val location: String = "",
    val instructor_id: Int,
    val numberOfAttendees: Int = 0,
    val max_size: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

class FitnessClassResult (val result: MutableList<FitnessClass>)