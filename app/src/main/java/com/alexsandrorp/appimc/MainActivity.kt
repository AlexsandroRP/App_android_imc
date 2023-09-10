package com.alexsandrorp.appimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text

//IMC - Classificação do IMC
//IMC = Peso ÷ (Altura × Altura)
//
//Menor que 16 - Magreza grave
//16 a menor que 17 - Magreza moderada
//17 a menor que 18,5 - Magreza leve
//18,5 a menor que 25 - Saudável
//25 a menor que 30 - Sobrepeso
//30 a menor que 35 - Obesidade Grau I
//35 a menor que 40 - Obesidade Grau II (considerada severa)
//Maior que 40 - Obesidade Grau III (considerada mórbida)

class MainActivity : AppCompatActivity() {
    private lateinit var textInputPeso: TextInputLayout
    private lateinit var editPeso: TextInputEditText

    private lateinit var textInputAltura: TextInputLayout
    private lateinit var editAltura: TextInputEditText

    private lateinit var btnCalcular: Button
    private lateinit var text_imc: TextView
    private lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarComponentesInterface()
        btnCalcular.setOnClickListener {
            calcularIMC()
        }
    }

    private fun calcularIMC() {
        val peso = editPeso.text.toString()
        val altura = editAltura.text.toString()



        val resultadoValidacao = validarCampos(peso, altura)
        if( resultadoValidacao ){
            val peso_convertido = peso.toDouble()
            val altura_convertida = altura.toDouble()

            val imc = peso_convertido / (altura_convertida * altura_convertida)
            val imcRounded = Math.round(imc * 10.0) / 10.0

            val classificacao = when {
                imc < 16 -> "Magreza grave"
                imc < 17 -> "Magreza moderada"
                imc < 18.5 -> "Magreza leve"
                imc < 25 -> "Saudável"
                imc < 30 -> "Sobrepeso"
                imc < 35 -> "Obesidade Grau I"
                imc < 35 -> "Obesidade Grau II"
                else -> "Obesidade Grau III"
            }
            val imc_convertido = imcRounded.toString()
            text_imc.text = "Seu índice IMC é: " + imc_convertido
            textResultado.text = "Sua classificação: " + classificacao
        }
    }

    private fun validarCampos(campo_peso: String, campo_altura: String): Boolean {

        textInputPeso.error = null
        textInputAltura.error = null

        if( campo_peso.isEmpty() || campo_altura.isEmpty() ){
            textInputPeso.error = "Digite seu peso"
            textInputAltura.error = "Digite sua altura"
            return false
        }

        return true
    }

    private fun inicializarComponentesInterface() {
        textInputPeso = findViewById(R.id.text_input_peso)
        editPeso = findViewById(R.id.edit_peso)

        textInputAltura = findViewById(R.id.text_input_altura)
        editAltura = findViewById(R.id.edit_altura)

        btnCalcular = findViewById(R.id.btn_calcular)
        text_imc = findViewById(R.id.text_imc)
        textResultado = findViewById(R.id.text_resultado)
    }
}