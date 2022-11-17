package com.ucaldas.todo

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

import com.ucaldas.todo.room_database.ClsToDo
import com.ucaldas.todo.room_database.ClsToDoDatabase
import com.ucaldas.todo.room_database.repository.ClsToDoRepository
import com.ucaldas.todo.room_database.viewmodel.ClsToDoViewModel


class ToDoFragment : Fragment(){

    private lateinit var listRecyclerView: RecyclerView
    private lateinit var myAdapter: RecyclerView.Adapter<MyTaskListAdapter.myViewHolder>
    var info:Bundle = Bundle()
    var myTaskTitles:ArrayList<String?> = ArrayList()
    var myTaskTimes:ArrayList<String?> = ArrayList()
    var myTaskPlaces:ArrayList<String?> = ArrayList()

    private lateinit var varTodoViewModel: ClsToDoViewModel
    private lateinit var varTodoRepository: ClsToDoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val myFragment: View=inflater.inflate(R.layout.fragment_to_do, container, false)
       /* val detail1: Button= myFragment.findViewById(R.id.btn_detail1)
        val detail2: Button= myFragment.findViewById(R.id.btn_detail2)
        val detail3: Button= myFragment.findViewById(R.id.btn_detail3)

        detail1.setOnClickListener(View.OnClickListener {
            val datos = Bundle()
            datos.putString("tarea", resources.getString(R.string.txt_tarea_1))
            datos.putString("hora","10:00")
            datos.putString("lugar","kwik-mart")
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(R.id.my_NavHostFragmentContainerView, DetailFragment::class.java, datos, "detail")
                ?.addToBackStack("")
                ?.commit()
        })



        detail2.setOnClickListener(View.OnClickListener {
            val datos = Bundle()
            datos.putString("tarea", resources.getString(R.string.txt_tarea_2))
            datos.putString("hora","08:01")
            datos.putString("lugar","Taller")
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(R.id.my_NavHostFragmentContainerView, DetailFragment::class.java, datos, "detail")
                ?.addToBackStack("")
                ?.commit()
        })



        detail3.setOnClickListener(View.OnClickListener {
            val datos = Bundle()
            datos.putString("tarea", resources.getString(R.string.txt_tarea_3))
            datos.putString("hora","20:10")
            datos.putString("lugar","Laundrymat")
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(R.id.my_NavHostFragmentContainerView, DetailFragment::class.java, datos, "detail")
                ?.addToBackStack("")
                ?.commit()
        })*/

        return myFragment
    }//override fun onCreateView(


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myFloActButton: View= requireActivity().findViewById(R.id.myFloatingActionButton)
        myFloActButton.setOnClickListener{view ->
            //add new task
            val valIntent = Intent(activity, NewTaskActivity::class.java)
            var varRequestCode=0; //New Task
            startActivityForResult(valIntent,varRequestCode)
        }




        /*

        myTaskTitles.add(resources.getString(R.string.txt_tarea_1))
        myTaskTitles.add(resources.getString(R.string.txt_tarea_2))
        myTaskTitles.add(resources.getString(R.string.txt_tarea_3))

        var myTaskTimes:ArrayList<String> = ArrayList()

        myTaskTimes.add("10:00")
        myTaskTimes.add("14:30")
        myTaskTimes.add("12:08")

        var myTaskPlaces:ArrayList<String> = ArrayList()

        myTaskPlaces.add("Kwik-mart")
        myTaskPlaces.add("Car workshop")
        myTaskPlaces.add("Laundermat")*/




        info.putStringArrayList("titles", myTaskTitles)
        info.putStringArrayList("times", myTaskTimes)
        info.putStringArrayList("places", myTaskPlaces)

        listRecyclerView = requireView().findViewById(R.id.myRecyclerViewTodoList)
        myAdapter = MyTaskListAdapter(activity as AppCompatActivity, info)

        listRecyclerView.setHasFixedSize(true)
        listRecyclerView.adapter = myAdapter
        listRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        myFunupdateList()


    }// override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



    private fun myFunupdateList() {
        val valDB = ClsToDoDatabase.getDatabase(requireActivity())
        val valdbFirebase = FirebaseFirestore.getInstance()
        val valIntfToDoDAO2 = valDB.myFunTodoDao()

        varTodoRepository = ClsToDoRepository(valIntfToDoDAO2)
        varTodoViewModel = ClsToDoViewModel(varTodoRepository)

        var varResult = varTodoViewModel.funGetAllTask()
        varResult.invokeOnCompletion {

            var varTheTask = varTodoViewModel.funGetTheTask()
            if(varTheTask!!.size!=0){
                var i = 0
                myTaskTitles.clear()
                myTaskTimes.clear()
                myTaskPlaces.clear()
                while (i<varTheTask.size){
                    myTaskTitles.add(varTheTask[i].title)
                    myTaskTimes.add(varTheTask[i].time)
                    myTaskPlaces.add(varTheTask[i].place)
                    i++
                }
                myAdapter.notifyDataSetChanged()
            }else{
                var varTasks = mutableListOf<ClsToDo>()
                valdbFirebase.collection("myCollectToDo").get().addOnSuccessListener {
                    var varDocs = it.documents
                    if(varDocs.size != 0){
                        var i =0
                        while(i < varDocs.size){
                            myTaskTitles.add(varDocs[i].get("title") as String)
                            myTaskTimes.add(varDocs[i].get("time") as String)
                            myTaskPlaces.add(varDocs[i].get("place") as String)
                            varTasks.add(ClsToDo(0,myTaskTitles[i],myTaskTimes[i],myTaskPlaces[i]))
                            i++
                        }
                        varTodoViewModel.funInsertTasks(varTasks)
                        myAdapter.notifyDataSetChanged()

                    }
                }//addOnSuccessListener
            }



        }

        /*runBlocking {
            launch {
                var varListResult = valIntfToDoDAO2.getAllRows()
                var i = 0
                myTaskTitles.clear()
                myTaskTimes.clear()
                myTaskPlaces.clear()
                while (i<varListResult.size){
                    myTaskTitles.add(varListResult[i].title)
                    myTaskTimes.add(varListResult[i].time)
                    myTaskPlaces.add(varListResult[i].place)
                    i++
                }
                myAdapter.notifyDataSetChanged()
            }
        }*/


    }// private fun myFunupdateList() {


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==0){// viene de crear una nueva actividad
            if(resultCode== Activity.RESULT_OK){//la nueva tarea fue creada exitosamente
                myFunupdateList()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}
