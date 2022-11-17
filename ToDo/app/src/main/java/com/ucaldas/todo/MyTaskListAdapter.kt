package com.ucaldas.todo

import android.os.Bundle
import android.os.RecoverySystem
import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MyTaskListAdapter (context: AppCompatActivity,
                        val info:Bundle):RecyclerView.Adapter<MyTaskListAdapter.myViewHolder>(){
        class myViewHolder (val layout: View): RecyclerView.ViewHolder(layout)

        private var context: AppCompatActivity= context

        var myTaskTitles: ArrayList<String> = info.getStringArrayList("titles") as ArrayList<String>
        var myTaskTimes: ArrayList<String> = info.getStringArrayList("times") as ArrayList<String>
        var myTaskPlaces: ArrayList<String> = info.getStringArrayList("places") as ArrayList<String>





        override fun getItemCount(): Int {
              return myTaskTitles.size
        }

        override fun onCreateViewHolder(myParent: ViewGroup, myViewType:Int): myViewHolder {
                val layout =LayoutInflater.from(myParent.context).inflate(R.layout.task_list_items, myParent,false)
                return myViewHolder(layout)
        }

        override fun onBindViewHolder(holder: myViewHolder, position: Int) {
                val textViewTask = holder.layout.findViewById<TextView>(R.id.myTextViewTask)
                textViewTask.text = myTaskTitles[position]

                val textViewTime = holder.layout.findViewById<TextView>(R.id.myTextViewTime)
                textViewTime.text = myTaskTimes[position]




                holder.layout.setOnClickListener{
                        Toast.makeText(holder.itemView.context, textViewTask.text, Toast.LENGTH_LONG).show()
                        val datos = Bundle()
                        datos.putString("tarea", textViewTask.text as String)
                        datos.putString("hora", textViewTime.text as String)
                        datos.putString("lugar", myTaskPlaces[position])
                        datos.putString("id", position.toString())


                        context?.getSupportFragmentManager()?.beginTransaction()
                                ?.setReorderingAllowed(true)
                                ?.replace(R.id.my_NavHostFragmentContainerView, DetailFragment::class.java, datos, "detail")
                                ?.addToBackStack("")
                                ?.commit()

                }

        }



}
