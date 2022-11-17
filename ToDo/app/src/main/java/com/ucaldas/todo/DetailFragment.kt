package com.ucaldas.todo

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.ucaldas.todo.room_database.ClsToDo
import com.ucaldas.todo.room_database.ClsToDoDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DetailFragment :Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmento =inflater.inflate(R.layout.fragment_detail, container, false)

        val task= requireArguments().getString("tarea")
        val hour= requireArguments().getString("hora")
        val place= requireArguments().getString("lugar")
        val id= requireArguments().getInt("idIn")

        val textViewTask: TextView=fragmento.findViewById(R.id.textViewTarea)
        val textViewHour: TextView=fragmento.findViewById(R.id.textViewHora)
        val textViewPlace: TextView=fragmento.findViewById(R.id.textViewLugar)


        textViewTask.text =task
        textViewHour.text =hour
        textViewPlace.text =place

        val btnEditar: Button = fragmento.findViewById(R.id.btnEdit)
        btnEditar.setOnClickListener{
            val principal = Intent(inflater.context, NewTaskActivity::class.java)
            principal.putExtra("tarea",task)
            principal.putExtra("hora",hour)
            principal.putExtra("lugar",place)
            principal.putExtra("id",id)
            startActivity(principal)
        }


        val btnDelete: Button = fragmento.findViewById(R.id.btnDelete)
        btnEditar.setOnClickListener{
            val myDB = ClsToDoDatabase.getDatabase(requireActivity())
            val valdbFirebase = FirebaseFirestore.getInstance()
            val myIntfToDoDAO = myDB.myFunTodoDao()
            val myTaskD = ClsToDo(id,task,hour,place)



            val negativeButton={ _: DialogInterface, _:Int-> }
            val positiveButton={ dialog:DialogInterface, which:Int -> }

            runBlocking {
                launch {
                    myIntfToDoDAO.deleteTask(myTaskD)
                    valdbFirebase.collection("myCollectToDo").document(id.toString()).delete()
                }
            }
            val principal = Intent(requireActivity() , ToDoFragment::class.java)
            startActivity(principal)

            val dialog = AlertDialog.Builder(requireActivity())
                .setTitle("DeleteTask")
                .setMessage("Are you sure?")
                .setPositiveButton("yes",positiveButton)
                .setNegativeButton("Cancelar",negativeButton)
                .create()
                .show()




        }


        return fragmento;
    }








}
