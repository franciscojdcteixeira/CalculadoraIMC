package com.comunidadedevspace.imc

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val KEY_RESULT_IMC = "ResultsActivity.KEY_IMC"

class ResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_results)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val resultado = intent.getFloatExtra(KEY_RESULT_IMC, 0f)

        val tvResult = findViewById<TextView>(R.id.tv_result)
        val tvClassificacao = findViewById<TextView>(R.id.tv_classificacao)
        val tvRiskLevel = findViewById<TextView>(R.id.tv_risk_level)

        tvResult.text = resultado.toString()

        val (classificacao, cor, nivelDeRisco) = when {
            resultado <= 18.5f -> Triple("MAGRO", R.color.color_magro, "Baixo")
            resultado > 18.5f && resultado <= 24.9f -> Triple("NORMAL", R.color.color_normal, "Muito Baixo")
            resultado >= 25f && resultado <= 29.9f -> Triple("SOBREPESO", R.color.color_sobrepeso, "Moderado")
            resultado >= 30f && resultado <= 39.9f -> Triple("OBESIDADE", R.color.color_obesidade, "Alto")
            else -> Triple("OBESIDADE GRAVE", R.color.color_obesidade_grave, "Muito Alto")
        }


        tvClassificacao.text = classificacao
        tvClassificacao.setTextColor(ContextCompat.getColor(this, cor))
        tvRiskLevel.text = "Nível de risco: $nivelDeRisco"

    }
}
