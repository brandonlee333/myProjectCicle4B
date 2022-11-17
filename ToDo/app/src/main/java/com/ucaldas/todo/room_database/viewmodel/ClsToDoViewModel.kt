package com.ucaldas.todo.room_database.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucaldas.todo.room_database.ClsToDo
import com.ucaldas.todo.room_database.repository.ClsToDoRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ClsToDoViewModel (private val valRepositoryIN: ClsToDoRepository) : ViewModel(){
var varTask: List<ClsToDo>?= null
    fun funGetAllTask(): Job {
        return viewModelScope.async{
            varTask = valRepositoryIN.funGetAllTask()
        }
    }


    fun funGetTheTask(): List<ClsToDo>?{
        return varTask
    }

    fun funInsertTask(toDoIN: ClsToDo): Long{
        var idTask: Long= 0
        viewModelScope.launch{
            idTask = valRepositoryIN.funInsertTask(toDoIN)
        }
        return idTask
    }



    fun funInsertTasks(toDoIN: List<ClsToDo>?) : List<Long>?{
        var varIdTask: List<Long>? = null
        viewModelScope.launch{
            varIdTask = valRepositoryIN.funInsertTasks(toDoIN)
        }
        return varIdTask
    }


}