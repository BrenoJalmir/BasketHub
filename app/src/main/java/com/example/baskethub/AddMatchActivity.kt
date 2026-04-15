package com.example.baskethub

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

class AddMatchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_match)

        val etTeamA = findViewById<EditText>(R.id.etTeamAName)
        val etScoreA = findViewById<EditText>(R.id.etScoreA)
        val etTeamB = findViewById<EditText>(R.id.etTeamBName)
        val etScoreB = findViewById<EditText>(R.id.etScoreB)
        val etDate = findViewById<EditText>(R.id.etDate)
        val etTime = findViewById<EditText>(R.id.etTime)
        val etQuarters = findViewById<EditText>(R.id.etQuarters)
        val btnSave = findViewById<Button>(R.id.btnSaveManualMatch)

        btnSave.setOnClickListener {
            val teamA = etTeamA.text.toString()
            val scoreA = etScoreA.text.toString().toIntOrNull()
            val teamB = etTeamB.text.toString()
            val scoreB = etScoreB.text.toString().toIntOrNull()
            val date = etDate.text.toString()
            val time = etTime.text.toString()
            val quarters = etQuarters.text.toString().toIntOrNull()

            if ((quarters ?: 0) <= 0) {
                Toast.makeText(this, "O número de quartos deve ser maior que zero", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isValidDate(date)) {
                Toast.makeText(this, "Data inválida. Use o formato DD/MM/AAAA", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isValidTime(time)) {
                Toast.makeText(this, "Hora inválida. Use o formato HH:MM", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!teamA.isEmpty() || !teamB.isEmpty() || scoreA != null || scoreB != null || quarters != null) {
                val match = Match(
                    teamA = teamA,
                    teamB = teamB,
                    scoreA = scoreA!!,
                    scoreB = scoreB!!,
                    dateTime = "$date $time",
                    quarters = quarters!!
                )
                saveMatch(match)
                finish()
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidDate(date: String): Boolean {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            sdf.isLenient = false
            sdf.parse(date)
            true
        } catch (_: Exception) {
            false
        }
    }

    private fun isValidTime(time: String): Boolean {
        return try {
            val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
            sdf.isLenient = false
            sdf.parse(time)
            true
        } catch (_: Exception) {
            false
        }
    }

    private fun saveMatch(match: Match) {
        val file = File(filesDir, "matches.json")
        val jsonArray = if (file.exists()) {
            JSONArray(file.readText())
        } else {
            JSONArray()
        }
        jsonArray.put(match.toJsonObject())
        file.writeText(jsonArray.toString())
        Toast.makeText(this, "Partida salva com sucesso!", Toast.LENGTH_SHORT).show()
    }
}