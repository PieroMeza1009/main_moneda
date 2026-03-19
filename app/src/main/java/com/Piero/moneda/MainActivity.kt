package com.Piero.moneda


import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtCantidad: EditText
    private lateinit var spOrigen: Spinner
    private lateinit var spDestino: Spinner
    private lateinit var btnConvertir: Button
    private lateinit var txtResultado: TextView

    private val monedas = arrayOf(
        "SOL", "USD", "EUR", "GBP", "INR", "BRL", "MXN", "CNY", "JPY"
    )

    // Tasas referenciales respecto al SOL
    private val tasas = mapOf(
        "SOL" to 1.0,
        "USD" to 3.65,
        "EUR" to 3.95,
        "GBP" to 4.60,
        "INR" to 0.044,
        "BRL" to 0.74,
        "MXN" to 0.21,
        "CNY" to 0.50,
        "JPY" to 0.024
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtCantidad = findViewById(R.id.edtCantidad)
        spOrigen = findViewById(R.id.spOrigen)
        spDestino = findViewById(R.id.spDestino)
        btnConvertir = findViewById(R.id.btnConvertir)
        txtResultado = findViewById(R.id.txtResultado)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, monedas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spOrigen.adapter = adapter
        spDestino.adapter = adapter

        btnConvertir.setOnClickListener {
            convertir()
        }
    }

    private fun convertir() {
        val texto = edtCantidad.text.toString().trim()

        if (texto.isEmpty()) {
            Toast.makeText(this, "Ingrese una cantidad", Toast.LENGTH_SHORT).show()
            return
        }

        val cantidad = texto.toDoubleOrNull()
        if (cantidad == null || cantidad <= 0) {
            Toast.makeText(this, "Ingrese un valor válido", Toast.LENGTH_SHORT).show()
            return
        }

        val origen = spOrigen.selectedItem.toString()
        val destino = spDestino.selectedItem.toString()

        val enSoles = cantidad * tasas[origen]!!
        val resultado = enSoles / tasas[destino]!!

        txtResultado.text = "%.2f %s = %.2f %s".format(cantidad, origen, resultado, destino)
    }
}