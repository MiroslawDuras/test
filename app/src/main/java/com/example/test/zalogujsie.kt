package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.FirebaseDatabase

class zalogujsie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.zalogujsie)

        val context = this
        val pacjentbutton = findViewById<Button>(R.id.pacjentbutton12)
        val lekarzbutton = findViewById<Button>(R.id.lekarzbutton12)
        val cofnijbutton = findViewById<Button>(R.id.cofnij1)
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

        myRef.setValue("Hello, World!")

        pacjentbutton.setOnClickListener {
            val intent = Intent(context, zalogujsiepacjent::class.java)
            startActivity(intent)
            finish()
        }
       lekarzbutton.setOnClickListener {
            val intent = Intent(context, zalogujsielekarz::class.java)
            startActivity(intent)
            finish()
        }

        cofnijbutton.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            finish()
        }




    }
}