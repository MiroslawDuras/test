package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val database1 = FirebaseDatabase.getInstance().reference
        //database1.setValue("Siema")
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")
        val context = this
        val zalogujsiebutton = findViewById<Button>(R.id.zalogujsie1)
        val zarejestrujsiebutton = findViewById<Button>(R.id.zarejestrujsie1)





        zalogujsiebutton.setOnClickListener{
            //database1.setValue("Siema")
            val intent = Intent(context, zalogujsie::class.java)
            startActivity(intent)
            finish()
        }

        zarejestrujsiebutton.setOnClickListener{
            //database1.setValue("Siema")
            val intent = Intent(context, zarejestrujsiepacjent::class.java)
            startActivity(intent)
            finish()
        }



    }
}