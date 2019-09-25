package com.example.anywherefitness.database

import androidx.room.*
import com.example.anywherefitness.model.FitnessClass
import com.example.anywherefitness.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fitnessClass: FitnessClass)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(fitnessClass: FitnessClass)

    @Delete
    fun delete(fitnessClass: FitnessClass)

    @Query("SELECT * FROM class_table")
    fun getAllFitnessClasses(): MutableList<FitnessClass>

    @Query("DELETE FROM class_table WHERE id = :id")
    fun deleteClassById(id: Int)

    @Query("DELETE FROM class_table")
    fun deleteAllClasses()
}