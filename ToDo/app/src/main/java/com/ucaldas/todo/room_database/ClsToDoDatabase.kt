package com.ucaldas.todo.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(ClsToDo::class), version = 1)
abstract class ClsToDoDatabase: RoomDatabase(){
   abstract fun myFunTodoDao():IntfToDoDAO

   companion object{
       @Volatile
       private var INSTANCE: ClsToDoDatabase? = null

       fun getDatabase(context: Context): ClsToDoDatabase {
           return INSTANCE ?: synchronized(this){
               val instance = Room.databaseBuilder(context.applicationContext,
                   ClsToDoDatabase::class.java,
               "clsToDoDatabase").build()
               INSTANCE = instance
               instance
           }
       }
   }
}