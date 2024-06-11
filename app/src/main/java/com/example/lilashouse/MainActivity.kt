package com.example.lilashouse

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var inputUser: EditText
    private lateinit var inputPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var abrirEsqueciSenha: TextView
    private lateinit var abrirCriarConta: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputUser = findViewById(R.id.input_user)
        inputPassword = findViewById(R.id.input_password)
        loginButton = findViewById(R.id.button_login)
        abrirEsqueciSenha = findViewById(R.id.textView_esqueci_senha)
        abrirCriarConta = findViewById(R.id.textview_criar_conta)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        loginButton.setOnClickListener {

            val username = inputUser.text.toString()
            val password = inputPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful && username != "victor@gmail.com") {
                            val backgroundColorLogin = ContextCompat.getColor(this, R.color.VerdeLogin)
                            showSnack(it, "Login realizado com sucesso", backgroundColorLogin)
                        } else {
                            showSnack(it, "Usuário ou senha incorretos", Color.GRAY)
                        }
                    }
            } else {
                showSnack(it, "Por favor, preencha todos os campos", Color.RED)
            }
        }
        abrirEsqueciSenha.setOnClickListener {
            val intent = Intent(this, EsqueciSenha::class.java)
            startActivity(intent)
        }

        abrirCriarConta.setOnClickListener {
            val intent = Intent(this, CadastrarConta::class.java)
            startActivity(intent)
        }

    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        // updateUI(currentUser)
    }
    private fun showSnack(view: View, message: String, color: Int) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(color)
        snackbar.show()
    }

}
