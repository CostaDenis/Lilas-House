package com.example.lilashouse

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class EsqueciSenha : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var InputEmail: EditText
    private lateinit var ButtonEmail: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_esqueci_senha)

        InputEmail = findViewById(R.id.input_email)
        ButtonEmail = findViewById(R.id.btn_EnviarEmail)

        auth = FirebaseAuth.getInstance()

        ButtonEmail.setOnClickListener {
            val email = InputEmail.text.toString()

            if(email.isEmpty()){
                showSnack(it, "Insira seu email!", Color.GRAY)
            } else {
                auth.sendPasswordResetEmail(email).addOnCompleteListener(this) { task->
                    if(task.isSuccessful){
                        showSnack(it, "Confira seu email!", Color.GREEN)
                    }
                } . addOnFailureListener {exception ->

                    val mensagemErro = when(exception){
                        is FirebaseNetworkException -> "Sem conexão com a Internet!"
                        is FirebaseAuthInvalidCredentialsException -> "Digite um email válido!"
                        else -> "Erro ao enviar o email"
                    }
                    showSnack(it, mensagemErro, Color.RED)
                }
            }
        }

        }

        private fun showSnack(view: View, message: String, color: Int) {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            snackbar.setBackgroundTint(color)
            snackbar.show()
        }
    }
