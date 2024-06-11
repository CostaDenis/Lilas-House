package com.example.lilashouse

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.example.lilashouse.databinding.ActivityClienteBinding

class activity_cliente : AppCompatActivity() {
    private var binding : ActivityClienteBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClienteBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding!!.btnPesqServ.setOnClickListener(){
            val intent = Intent(this, PesquisaServico::class.java)
            startActivity(intent)
        }
        binding!!.btnEditaPerfil.setOnClickListener(){
            val intent = Intent(this, EditarPerfil::class.java)
            startActivity(intent)
        }
        binding!!.btnVerServ.setOnClickListener(){
            val intent = Intent(this, ServicosCliente::class.java)
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