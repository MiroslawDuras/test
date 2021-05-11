package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.*
import java.lang.StringBuilder

class zapiszsienawizyte : AppCompatActivity() {

   lateinit var database1:FirebaseDatabase
    lateinit var database2:FirebaseDatabase
    lateinit var reference: DatabaseReference
    lateinit var reference2: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zapiszsienawizyte)
        val wroc = findViewById<Button>(R.id.wroc)
        val lekarzedostepni = findViewById<TextView>(R.id.dostepnilekarze)
        val context = this
         var database1=FirebaseDatabase.getInstance().getReference("Lekarze")
       // reference=database1.getReference("Users")




        var getData = object  : ValueEventListener{
            override fun onCancelled(p0 : DatabaseError){

            }
            override fun onDataChange(p0: DataSnapshot){
                var sb = StringBuilder()
                for(i in p0.children){
                    var name = i.child("name").getValue()
                    var nazwisko = i.child("nazwisko").getValue()
                    var miasto = i.child("miasto").getValue()
                    var jakiLekarz = i.child("jakiLekarz").getValue()
                    sb.append("$name $nazwisko, $miasto,  $jakiLekarz")
                    sb.append(" ")
                    sb.append(" ")

                }
                lekarzedostepni.setText(sb)

            }
        }
        database1.addValueEventListener(getData)
        database1.addListenerForSingleValueEvent(getData)

        wroc.setOnClickListener {
            val intent = Intent(context, profilpacjenta::class.java)
            startActivity(intent)
            finish()
        }
    }


}