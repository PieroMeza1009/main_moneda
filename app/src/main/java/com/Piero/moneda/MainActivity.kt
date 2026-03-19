package com.Piero.moneda

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextCantidad: EditText
    private lateinit var radioDolares: RadioButton
    private lateinit var radioSoles: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextCantidad = findViewById(R.id.editTextText)
        radioDolares = findViewById(R.id.radio0)
        radioSoles = findViewById(R.id.radio1)
    }

    fun miClicManejador(view: View) {
        when (view.id) {
            R.id.bntConvertir -> convertirMoneda()
        }
    }

    private fun convertirMoneda() {
        val cantidadTexto = editTextCantidad.text.toString().trim()

        if (cantidadTexto.isEmpty()) {
            mostrarError("Ingresa una cantidad")
            return
        }

        val cantidad = cantidadTexto.toFloatOrNull()

        if (cantidad == null || cantidad <= 0) {
            mostrarError("Ingresa un número válido mayor a 0")
            return
        }

        when {
            radioDolares.isChecked -> {
                val soles = convierteDolaresASoles(cantidad)
                mostrarResultado("%.2f dólares = %.2f soles".format(cantidad, soles))

            }

            radioSoles.isChecked -> {
                val dolares = convierteSolesADolares(cantidad)
                mostrarResultado("%.2f soles = %.2f dólares".format(cantidad, dolares))
            }

            else -> {
                mostrarError("Selecciona una moneda para convertir")
            }
        }
    }

    private fun convierteDolaresASoles(dolares: Float): Float {
        val tipoCambio = 3.65f
        return dolares * tipoCambio
    }

    private fun convierteSolesADolares(soles: Float): Float {
        val tipoCambio = 3.65f
        return soles / tipoCambio
    }

    private fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        editTextCantidad.requestFocus()
    }

    private fun mostrarResultado(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}