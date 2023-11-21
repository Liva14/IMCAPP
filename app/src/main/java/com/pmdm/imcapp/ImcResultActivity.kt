package com.pmdm.imcapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class ImcResultActivity : AppCompatActivity() {
    private lateinit var btnRecalcular: AppCompatButton
    private lateinit var tituloHeight: TextView
    private lateinit var textHeightR:TextView
    private lateinit var textExplicacion: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_result)
        initComponent()
        initListeners()
        initUI()
    }

    private fun initComponent() {
        btnRecalcular = findViewById(R.id.btnRecalcular)
        tituloHeight = findViewById(R.id.tituloHeight)
        textHeightR = findViewById(R.id.textHeightR)
        textExplicacion = findViewById(R.id.textExplicacion)
    }

    private fun initUI() {
        val titulo:String =intent.extras?.getString("Título").orEmpty()
        tituloHeight.text = titulo
        val res:String? = intent.extras?.getString("Resultado")
        textHeightR.text = res.toString()
        val desc =
            intent.extras?.getString("Descripción").orEmpty()
        textExplicacion.text = desc
    }

    private fun initListeners() {
        btnRecalcular.setOnClickListener{
            navigateRecalc()
        }
    }

    private fun navigateRecalc() {
        val intent = Intent (this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }
}