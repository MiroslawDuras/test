package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class profilpacjenta : AppCompatActivity() {

    lateinit var  auth : FirebaseAuth
    var databaseReference : DatabaseReference? = null
        var database: FirebaseDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilpacjenta)
        Toast.makeText(this, "Pomyślnie zalogowano", Toast.LENGTH_SHORT).show()
        val wyloguj = findViewById<Button>(R.id.wyloguj)
        val poleTekstowe = findViewById<TextView>(R.id.textView)



        auth = FirebaseAuth.getInstance()

        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("Users")

        val user = auth.currentUser
        val userref = databaseReference?.child(user?.uid!!)
       // val database = FirebaseDatabase.getInstance()
       // val myRef = database.getReference("message")

        //myRef.setValue("Hello, World!")

        userref?.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                poleTekstowe.text= ("Witaj " + snapshot.child("name").value.toString())

            }
        })


        wyloguj.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this@profilpacjenta, MainActivity::class.java))
            finish()

        }
    }

}