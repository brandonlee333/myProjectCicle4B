package com.ucaldas.todo.room_database.repository

import com.ucaldas.todo.room_database.ClsToDo
import com.ucaldas.todo.room_database.IntfToDoDAO


class ClsToDoRepository (private val toDoDaoIN: IntfToDoDAO) {
    suspend fun funGetAllTask(): List<ClsToDo>{
        return toDoDaoIN.getAllRows()
    }

    suspend fun funInsertTask(toDoIN : ClsToDo):Long{
        return toDoDaoIN.insertTask(toDoIN)
    }

    suspend fun funInsertTasks(toDoIN: List<ClsToDo>?): List<Long>{
        return toDoDaoIN.insertTasks(toDoIN)
    }

}