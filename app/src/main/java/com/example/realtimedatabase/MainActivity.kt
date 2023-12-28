package com.example.realtimedatabase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Timestamp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    val db = FirebaseDatabase.getInstance().getReference("users")
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = findViewById<EditText>(R.id.editName)
        val email = findViewById<EditText>(R.id.editEmail)
        val phone = findViewById<EditText>(R.id.editPhone)
        val insertBtn = findViewById<AppCompatButton>(R.id.nsert)



        insertBtn.setOnClickListener {
            val date = Timestamp.now().nanoseconds

          val user = UserModel("$date",name.text.toString(),email.text.toString(),phone.text.toString().toLong())

            db.child("$date").setValue(user)
                .addOnSuccessListener {
                    Toast.makeText(this, "$date is stored", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
        }

        val userArray = ArrayList<UserModel?>()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        db.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (result in snapshot.children){
                    val userModel = result.getValue(UserModel::class.java)
                    userArray.add(userModel)
                }
                recyclerView.adapter = UserAdapter(userArray)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}