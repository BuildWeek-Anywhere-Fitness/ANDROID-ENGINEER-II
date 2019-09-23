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

    @Query("SELECT * FROM user_table")
    fun getAllFitnessClasses(): List<FitnessClass>


}