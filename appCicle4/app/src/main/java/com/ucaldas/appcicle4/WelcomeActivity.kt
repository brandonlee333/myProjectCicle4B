package com.ucaldas.appcicle4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar

class WelcomeActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val varFab: View=findViewById(R.id.fab)
        varFab.setOnClickListener{view ->
            Snackbar.make(view,R.string.txFab,Snackbar.LENGTH_LONG)
                .setAction("Action",null)
                .show()
        }
    }
}