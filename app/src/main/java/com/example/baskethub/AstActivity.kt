package com.example.baskethub

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.util.Locale

class AstActivity : AppCompatActivity() {

    private lateinit var etAst: EditText
    private lateinit var etFga: EditText
    private lateinit var etFta: EditText
    private lateinit var etTo: EditText
    private lateinit var tvResultadoAst: TextView
    private lateinit var tvFormulaAst: TextView
    private lateinit var btnCalcularAst: MaterialButton
    private lateinit var btnLimparAst: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ast)

        etAst = findViewById(R.id.etAst)
        etFga = findViewById(R.id.etFga)
        etFta = findViewById(R.id.etFta)
        etTo = findViewById(R.id.etTo)
        tvResultadoAst = findViewById(R.id.tvResultadoAst)
        tvFormulaAst = findViewById(R.id.tvFormulaAst)
        btnCalcularAst = findViewById(R.id.btnCalcularAst)
        btnLimparAst = findViewById(R.id.btnLimparAst)

        btnCalcularAst.setOnClickListener {
            calcularAstRatio()
        }

        btnLimparAst.setOnClickListener {
            limparCampos()
        }
    }

    private fun calcularAstRatio() {
        val ast = etAst.text.toString().replace(",", ".").toDoubleOrNull()
        val fga = etFga.text.toString().replace(",", ".").toDoubleOrNull()
        val fta = etFta.text.toString().replace(",", ".").toDoubleOrNull()
        val turnovers = etTo.text.toString().replace(",", ".").toDoubleOrNull()

        if (ast == null || fga == null || fta == null || turnovers == null) {
            Toast.makeText(this, "Preencha todos os campos corretamente.", Toast.LENGTH_SHORT).show()
            return
        }

        val denominador = fga + (0.44 * fta) + ast + turnovers

        if (denominador == 0.0) {
            Toast.makeText(this, "O denominador não pode ser zero.", Toast.LENGTH_SHORT).show()
            return
        }

        val astRatio = (ast / denominador) * 100

        tvResultadoAst.text = String.format(Locale.US, "AST Ratio: %.2f%%", astRatio)
        tvFormulaAst.text = String.format(
            Locale.US,
            "Cálculo: (%.2f ÷ (%.2f + (0.44 × %.2f) + %.2f + %.2f)) × 100 = %.2f%%",
            ast, fga, fta, ast, turnovers, astRatio
        )
    }

    private fun limparCampos() {
        etAst.text.clear()
        etFga.text.clear()
        etFta.text.clear()
        etTo.text.clear()
        tvResultadoAst.text = "AST Ratio: 0.00%"
        tvFormulaAst.text = "Cálculo: —"
    }
}