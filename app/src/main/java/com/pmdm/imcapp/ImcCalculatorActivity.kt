package com.pmdm.imcapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class ImcCalculatorActivity : AppCompatActivity() {
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var viewWeight: CardView
    private lateinit var viewAge: CardView
    private lateinit var tvWeight: TextView
    private lateinit var tvAge: TextView
    private lateinit var btnAddWeight: FloatingActionButton
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnAddAge: FloatingActionButton
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var calcButton: AppCompatButton

    private var isComponentSelected: Boolean = true
    private var weight: Double = 40.0
    private var age: Double = 18.0
    private var height: Double = 120.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)
        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        viewWeight = findViewById(R.id.viewWeight)
        viewAge = findViewById(R.id.viewAge)
        tvWeight = findViewById(R.id.tvWeight)
        tvAge = findViewById(R.id.tvAge)
        btnAddWeight = findViewById(R.id.btnAddWeight)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnAddAge = findViewById(R.id.btnAddAge)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        calcButton = findViewById(R.id.calcButton)
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            isComponentSelected = true
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            isComponentSelected = false
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            tvHeight.text = DecimalFormat("#.##").format(value)
        }
        btnAddWeight.setOnClickListener {
            weight += 1
            setWeight()
        }
        btnAddAge.setOnClickListener {
            age += 1
            setAge()
        }
        btnSubtractWeight.setOnClickListener {
            weight -= 1
            setWeight()
        }
        btnSubtractAge.setOnClickListener {
            age -= 1
            setAge()
        }
        calcButton.setOnClickListener {
            calculateIMC()
        }

    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(
            getBackgroundColor(isComponentSelected)
        )
        viewFemale.setCardBackgroundColor(
            getBackgroundColor(!isComponentSelected)
        )
    }

    private fun getBackgroundColor(isComponentSelected: Boolean): Int {
        val colorReference = if (isComponentSelected) {
            R.color.bg_comp_sel
        } else {
            R.color.bg_comp
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }

    private fun setWeight() {
        tvWeight.setText(weight.toString())
    }

    private fun setAge() {
        tvAge.setText(age.toString())
    }

    private fun calculateIMC(): Double {
        return weight / ((height / 100) * (height / 100))
    }

    private fun navigate2result(imc: Double) {

        var desc: String
        var titulo: String

        when {
            imc < 18.5 -> {
                titulo = "Bajo peso"
                desc = "Come más que te vas a morir."
            }

            imc in 18.5..24.9 -> {
                titulo = "Peso normal"
                desc = "DPM, no dejes de comer, pero tampoco te pases."
            }

            imc in 25.0..29.9 -> {
                titulo = "Sobrepeso"
                desc = "Deja de comer y ponte a correr."
            }

            imc > 30.0 -> {
                titulo = "Obesidad"
                desc = "Tu flipao, menos bollycao y mas brocoli."
            }

            else -> {
                titulo = ""
                desc = ""
            }
        }

        val intent = Intent(this, ImcResultActivity::class.java)

        intent.putExtra("RESULTADO_EXTRA", imc)
        intent.putExtra("TITULO_EXTRA", titulo)
        intent.putExtra("TEXTO_EXTRA", desc)

        startActivity(intent)
    }
}
