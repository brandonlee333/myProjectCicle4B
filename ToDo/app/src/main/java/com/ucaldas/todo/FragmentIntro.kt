package com.ucaldas.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentIntro : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_text)
    }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val fragmento =inflater.inflate(R.layout.fragment_intro, container, false)
            return fragmento;
        }

/*
        val varFab: View =findViewById(R.id.fab)
        varFab.setOnClickListener{view ->
            Snackbar.make(view,R.string.txFab, Snackbar.LENGTH_LONG)
                .setAction("Action",null)
                .show()
        }*/


}