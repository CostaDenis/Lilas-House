package com.example.lilashouse

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
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
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputUser = findViewById(R.id.input_user)
        inputPassword = findViewById(R.id.input_password)
        loginButton = findViewById(R.id.button_login)
        abrirEsqueciSenha = findViewById(R.id.textView_esqueci_senha)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        loginButton.setOnClickListener {

            val username = inputUser.text.toString()
            val password = inputPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val backgroundColorLogin = ContextCompat.getColor(this, R.color.VerdeLogin)
                            val snackbar_Sucesso = Snackbar.make(it, "Login realizado com sucesso", Snackbar.LENGTH_SHORT)
                            snackbar_Sucesso.setBackgroundTint(backgroundColorLogin)
                            snackbar_Sucesso.show()
                        } else {
                            val snackbar_Incorreto = Snackbar.make(it, "Usuário ou senha incorretos", Snackbar.LENGTH_SHORT)
                            snackbar_Incorreto.setBackgroundTint(Color.GRAY)
                            snackbar_Incorreto.show()
                        }
                    }
            } else {
                val snackbar_Erro = Snackbar.make(it, "Por favor, preencha todos os campos", Snackbar.LENGTH_SHORT)
                snackbar_Erro.setBackgroundTint(Color.RED)
                snackbar_Erro.show()
            }
        }
        abrirEsqueciSenha.setOnClickListener {
            val intent = Intent(this, EsqueciSenha::class.java)
            startActivity(intent)
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
