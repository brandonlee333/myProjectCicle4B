package com.ucaldas.todo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

import com.ucaldas.todo.room_database.ClsToDo
import com.ucaldas.todo.room_database.ClsToDoDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class NewTaskActivity: AppCompatActivity() {

    lateinit var editTextTitle: EditText
    lateinit var editTextTime: EditText
    lateinit var editTextPlace: EditText
    lateinit var editTextId: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        editTextTitle = findViewById(R.id.myEditTextTitle)
        editTextTime = findViewById(R.id.myEditTextTime)
        editTextPlace = findViewById(R.id.myEditTextPlace)
        //editTextId = findViewById(R.id.myEditTextId)

        if(this.intent.getStringExtra("id")!=null){
            editTextTitle.setText(this.intent.getStringExtra("tarea"))
            editTextTime.setText(this.intent.getStringExtra("hora"))
            editTextPlace.setText(this.intent.getStringExtra("lugar"))
            editTextId.setText(this.intent.getStringExtra("id"))
        }
    }

    fun myFunOnSave(view: View){

        var vartitle: String = editTextTime.text.toString()
        var vartime: String = editTextTime.text.toString()
        var varplace: String = editTextTime.text.toString()
        var varidd: String = editTextId.text.toString()

        val myDB = ClsToDoDatabase.getDatabase(this)
        val valdbFirebase = FirebaseFirestore.getInstance()
        val myIntfToDoDAO = myDB.myFunTodoDao()

        val myTask = ClsToDo(0, editTextTitle.text.toString(), editTextTime.text.toString(), editTextPlace.text.toString())
        var myResult: Long? = null

        runBlocking {
            launch {

                if(varidd.equals("0")){
                    myResult = myIntfToDoDAO.insertTask(myTask)

                    if(myResult!=-1L){


                        valdbFirebase.collection("myCollectToDo").document(myResult.toString()).set(
                            hashMapOf(
                                "title" to editTextTitle.text.toString(),
                                "time" to editTextTime.text.toString(),
                                "place" to editTextPlace.text.toString()
                            ))
                        setResult(Activity.RESULT_OK) // informa q todo salio al pelo
                        finish()
                    }
                }else{
                    valdbFirebase.collection("myCollectToDo").document(varidd)
                        .set(
                            hashMapOf(
                                "title" to vartitle,
                                "time" to vartime,
                                "place" to varplace
                            )
                        )
                    myIntfToDoDAO.updateTask(myTask)
                    finish()
                }

            }
        }
        val principal = Intent(this, ToDoFragment::class.java)
        startActivity(principal)
    }//fun myFunOnSave(view: View){








}
