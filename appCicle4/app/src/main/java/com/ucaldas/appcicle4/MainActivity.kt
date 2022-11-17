package com.ucaldas.appcicle4

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.security.interfaces.RSAKey

class MainActivity : AppCompatActivity() {
    private var myedtUser: EditText? = null
    private var myedtPass: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolBar))


        myedtUser =findViewById(R.id.edtUsername)
        myedtPass =findViewById(R.id.edtPass)
    }

    fun onLogin(view: View) {
        var strUsername:String = myedtUser!!.text.toString()
        var strPassword:String = myedtPass!!.text.toString()
        if (strUsername=="pepe" && strPassword=="1234"){
           /* val intento =Intent(this,WelcomeActivity::class.java)
            startActivity(intento)*/


            val positiveButton={
                    dialog: DialogInterface, which: Int ->
                val intento2=Intent(this,WelcomeActivity::class.java)
                startActivity(intento2)
            }
            val dialog = AlertDialog.Builder(this)
                .setTitle("WELCOME!")
                .setMessage("User: "+ myedtPass!!.text.toString())
                .setPositiveButton("OK",positiveButton )
            dialog.create()
            dialog.show()
        }else{
            val dialog =AlertDialog.Builder(this).setTitle("ERROR!").setMessage("Invalid username or password").create().show()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean = when(item.itemId){
        R.id.action_search -> {
            Toast.makeText(this,R.string.txAction_search, Toast.LENGTH_LONG).show()
            true
        }
        R.id.action_settings -> {
            Toast.makeText(this,R.string.txAction_settings, Toast.LENGTH_LONG).show()
            true
        }
        R.id.action_logOut -> {
            Toast.makeText(this,R.string.txAction_logout, Toast.LENGTH_LONG).show()
            true
        }
        else ->{
            super.onOptionsItemSelected(item)
        }
    }

}