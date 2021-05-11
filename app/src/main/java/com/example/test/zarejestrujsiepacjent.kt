package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.auth.AuthResult
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser



class zarejestrujsiepacjent : AppCompatActivity() {

    lateinit var  auth : FirebaseAuth
    //var databaseReference : DatabaseReference? = null
    //var database: FirebaseDatabase? = null
    lateinit var database1:FirebaseDatabase
    lateinit var database2:FirebaseDatabase
    lateinit var reference:DatabaseReference
    lateinit var reference2:DatabaseReference




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.zarejestrujsiepacjent)

        val context = this
        val cofnijbutton = findViewById<Button>(R.id.cofnij6)
        val zarejestrujsiebutton = findViewById<Button>(R.id.zalogujsiebutton2)
        val poleEmail = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val poleHaslo = findViewById<EditText>(R.id.editTextTextPassword)
        val poleImie= findViewById<EditText>(R.id.editTextName)
        val poleNazwisko = findViewById<EditText>(R.id.editTextSurname)


        auth = FirebaseAuth.getInstance()
        database1 = FirebaseDatabase.getInstance()
        database2=FirebaseDatabase.getInstance()
        reference=database1.getReference("Users")
        reference2=database1.getReference("Lekarze")
        //database = FirebaseDatabase.getInstance()
        //databaseReference = database?.reference!!.child("ProfilUzytkownika")


        zarejestrujsiebutton.setOnClickListener {



                if(poleImie.text.toString().isEmpty())
                {
                    Toast.makeText(this@zarejestrujsiepacjent, "Prosze wpisać swoje imie", Toast.LENGTH_LONG).show()

                }

                if(poleNazwisko.text.toString().isEmpty())
                {
                    Toast.makeText(this@zarejestrujsiepacjent, "Prosze wpisać swoje naziwsko", Toast.LENGTH_LONG).show()

                }

                if(poleEmail.text.toString().isEmpty())
                {
                    Toast.makeText(this@zarejestrujsiepacjent, "Prosze wpisać adres email", Toast.LENGTH_LONG).show()

                }

                if(poleHaslo.text.toString().isEmpty())
                {
                    Toast.makeText(this@zarejestrujsiepacjent, "Prosze wpisać hasło", Toast.LENGTH_LONG).show()

                }

            else
            {
                val email: String = poleEmail.text.toString()
                val password: String = poleHaslo.text.toString()
                val name: String = poleImie.text.toString()
                val surname: String = poleNazwisko.text.toString()


                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->
                    if(task.isSuccessful){
                        val firebaseUser: FirebaseUser = task.result!!.user!!

                        Toast.makeText(this@zarejestrujsiepacjent, "Zostałeś pomyślnie zarejestrowany!", Toast.LENGTH_LONG).show()

                        val currentUser = auth.currentUser

                        var model = DaneUzytkownika(name, surname, email, password)
                        var id=reference.push().key
                        reference.child(id!!).setValue(model)

                        var model1 = DaneLekarzy("Stanisław", "Mazur", "stan@gmail.com", "123456", "Wrocław", "Kardiolog")
                        var model2 = DaneLekarzy("Maciej", "Kania", "mac@gmail.com", "123456", "Warszawa", "Anestezjolog")
                        var model3 = DaneLekarzy("Karolina", "Pieczarka", "kar@gmail.com", "123456", "Wieluń", "Gastrolog")
                        var id2=reference2.push().key
                        reference.child(id2!!).setValue(model1)
                        reference.child(id2!!).setValue(model2)
                        reference.child(id2!!).setValue(model3)


                       // val currentUSerDatabase = databaseReference?.child((currentUser?.uid!!))
                        //currentUSerDatabase?.child("imie")?.setValue(name)
                        //currentUSerDatabase?.child("nazwisko")?.setValue(surname)
                        //currentUSerDatabase?.child("rola")?.setValue("pacjent")


                        val intent = Intent(this@zarejestrujsiepacjent, pomyslniezarejestrowanopacjent::class.java)
                        intent.putExtra("user_id", firebaseUser.uid)
                        intent.putExtra("email_id", email)
                        startActivity(intent)
                        finish()
                    }
                        else
                    {
                        Toast.makeText(this@zarejestrujsiepacjent,task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                })
            }

            }

        cofnijbutton.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}