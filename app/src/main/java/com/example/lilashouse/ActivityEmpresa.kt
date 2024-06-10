package com.example.lilashouse

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lilashouse.databinding.ActivityEmpresaBinding

class ActivityEmpresa : AppCompatActivity() {
    private var binding : ActivityEmpresaBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmpresaBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding!!.btnVerPedido.setOnClickListener(){
            val intent = Intent(this, VerPedidos::class.java)
            startActivity(intent)
        }
        binding!!.btnCadastroServ.setOnClickListener(){
            val intent = Intent(this, CadastraServico::class.java)
            startActivity(intent)
        }
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}