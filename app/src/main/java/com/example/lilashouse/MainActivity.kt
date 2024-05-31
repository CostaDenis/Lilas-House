package com.example.lilashouse

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {

    private lateinit var inputUser: EditText
    private lateinit var inputPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputUser = findViewById(R.id.input_user)
        inputPassword = findViewById(R.id.input_password)
        loginButton = findViewById(R.id.button_login)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        loginButton.setOnClickListener {

            val username = inputUser.text.toString()
            val password = inputPassword.text.toString()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            showToast("Login realizado com sucesso")
                        } else {
                            showToast("Usuário ou senha incorretos")
                        }
                    }
            } else {
                showToast("Por favor, preencha todos os campos")
            }
        }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        // updateUI(currentUser)
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
