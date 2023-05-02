package com.example.realtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myfirebase_1.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var count: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database =  FirebaseDatabase.getInstance();
        val myRefer = database.getReference()

        save.setOnClickListener {
            val name = PersonName.text.toString()
            val id = PersonID.text.toString()
            val age = PersonAge.text.toString()


            val person = hashMapOf(
                "name" to name,
                "id" to id,
                "age" to age
            )

            myRefer.child("person").child("$count").setValue(person)
            count++
            Toast.makeText(applicationContext, "SuccessData", Toast.LENGTH_SHORT).show()

        }
        get.setOnClickListener {
            // Read from the database
            myRefer.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = snapshot.getValue()
                    textData.text = value.toString()
                    Toast.makeText(applicationContext, "SuccessData", Toast.LENGTH_SHORT).show()
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "ErrorData", Toast.LENGTH_SHORT).show()
                }

            })

        }
    }
}