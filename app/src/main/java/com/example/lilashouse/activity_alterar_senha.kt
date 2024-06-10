package com.example.lilashouse

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lilashouse.databinding.ActivityAlterarSenhaBinding
import com.google.firebase.firestore.FirebaseFirestore

const val TAG = "FIRESTORE"

class activity_alterar_senha : AppCompatActivity() {

    private var binding : ActivityAlterarSenhaBinding? = null
    val fireStoreDatabase = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlterarSenhaBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding!!.btnEnviar.setOnClickListener(){
            //Aqui deveria ficar o código depois do usuário clicar no botão de redefinir senha
        }

        enableEdgeToEdge()
        //setContentView(R.layout.activity_alterar_senha)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}