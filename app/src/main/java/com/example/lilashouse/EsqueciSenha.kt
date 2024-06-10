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
import com.google.firebase.auth.FirebaseAuth

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
            val email = InputEmail.toString()

            if(email.isEmpty()){
                val snackbar_Erro = Snackbar.make(it, "Insira seu email!", Snackbar.LENGTH_SHORT)
                snackbar_Erro.setBackgroundTint(Color.GRAY)
                snackbar_Erro.show()
            } else {
                auth.sendPasswordResetEmail(email).addOnCompleteListener(this) { task->
                    if(task.isSuccessful){
                        val snackbar_Sucesso = Snackbar.make(it, "Insira seu email!", Snackbar.LENGTH_SHORT)
                        snackbar_Sucesso.setBackgroundTint(Color.GRAY)
                        snackbar_Sucesso.show()
                    }
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
