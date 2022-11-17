package com.ucaldas.todo.room_database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface IntfToDoDAO {
    @Query("SELECT *  FROM ClsToDo")
    suspend fun getAllRows(): List<ClsToDo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: ClsToDo): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTasks(task: List<ClsToDo>?): List<Long>


    @Update
    suspend fun updateTask(task: ClsToDo)

    @Delete
    suspend fun deleteTask(task: ClsToDo)
}