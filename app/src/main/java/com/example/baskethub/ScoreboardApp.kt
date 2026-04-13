package com.example.baskethub

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import java.util.Locale

class ScoreboardApp : ComponentActivity() {
    private var pontuacaoTimeA: Int = 0
    private var pontuacaoTimeB: Int = 0

    private lateinit var pTimeA: TextView
    private lateinit var pTimeB: TextView
    private lateinit var etTimeA: EditText
    private lateinit var etTimeB: EditText

    // Cronômetro
    private lateinit var tvTimer: TextView
    private lateinit var btnStartPause: Button
    private lateinit var btnResetTimer: Button

    // Botões de pontuação
    private lateinit var bTresPontosTimeA: Button
    private lateinit var bDoisPontosTimeA: Button
    private lateinit var bTLivreTimeA: Button
    private lateinit var bTresPontosTimeB: Button
    private lateinit var bDoisPontosTimeB: Button
    private lateinit var bTLivreTimeB: Button

    private var countDownTimer: CountDownTimer? = null
    private var isTimerRunning: Boolean = false
    private var timeLeftInMillis: Long = 600000 // 10 minutos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scoreboard)

        pTimeA = findViewById(R.id.placarTimeA)
        pTimeB = findViewById(R.id.placarTimeB)
        etTimeA = findViewById(R.id.timeA)
        etTimeB = findViewById(R.id.timeB)

        tvTimer = findViewById(R.id.tvTimer)
        btnStartPause = findViewById(R.id.btnStartPause)
        btnResetTimer = findViewById(R.id.btnResetTimer)

        bTresPontosTimeA = findViewById(R.id.tresPontosA)
        bDoisPontosTimeA = findViewById(R.id.doisPontosA)
        bTLivreTimeA = findViewById(R.id.tiroLivreA)
        bTresPontosTimeB = findViewById(R.id.tresPontosB)
        bDoisPontosTimeB = findViewById(R.id.doisPontosB)
        bTLivreTimeB = findViewById(R.id.tiroLivreB)
        val bReiniciarPartida: Button = findViewById(R.id.reiniciarPartida)

        bTresPontosTimeA.setOnClickListener { adicionarPontos(3, "A") }
        bDoisPontosTimeA.setOnClickListener { adicionarPontos(2, "A") }
        bTLivreTimeA.setOnClickListener { adicionarPontos(1, "A") }
        bTresPontosTimeB.setOnClickListener { adicionarPontos(3, "B") }
        bDoisPontosTimeB.setOnClickListener { adicionarPontos(2, "B") }
        bTLivreTimeB.setOnClickListener { adicionarPontos(1, "B") }
        bReiniciarPartida.setOnClickListener { reiniciarPartida() }

        btnStartPause.setOnClickListener {
            if (isTimerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }

        btnResetTimer.setOnClickListener {
            resetTimer()
        }

        if (savedInstanceState != null) {
            pontuacaoTimeA = savedInstanceState.getInt("pontuacaoTimeA")
            pontuacaoTimeB = savedInstanceState.getInt("pontuacaoTimeB")
            timeLeftInMillis = savedInstanceState.getLong("timeLeftInMillis")
            isTimerRunning = savedInstanceState.getBoolean("isTimerRunning")
            etTimeA.setText(savedInstanceState.getString("nomeTimeA", "Time A"))
            etTimeB.setText(savedInstanceState.getString("nomeTimeB", "Time B"))

            pTimeA.text = pontuacaoTimeA.toString()
            pTimeB.text = pontuacaoTimeB.toString()

            if (isTimerRunning) {
                startTimer()
            }
        }

        updateCountDownText()
        updatePointButtonsState()
    }

    private fun startTimer() {
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                isTimerRunning = false
                btnStartPause.setText(R.string.play)
                updatePointButtonsState()
                Toast.makeText(this@ScoreboardApp, "Tempo esgotado!!", Toast.LENGTH_LONG).show()
            }
        }.start()

        isTimerRunning = true
        btnStartPause.setText(R.string.pause)
        updatePointButtonsState()
    }

    private fun pauseTimer() {
        countDownTimer?.cancel()
        isTimerRunning = false
        btnStartPause.setText(R.string.play)
        updatePointButtonsState()
    }

    private fun resetTimer() {
        countDownTimer?.cancel()
        isTimerRunning = false
        timeLeftInMillis = 600000
        updateCountDownText()
        btnStartPause.setText(R.string.play)
        updatePointButtonsState()
    }

    private fun updateCountDownText() {
        val minutes = (timeLeftInMillis / 1000).toInt() / 60
        val seconds = (timeLeftInMillis / 1000).toInt() % 60
        val timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        tvTimer.text = timeLeftFormatted
    }

    private fun updatePointButtonsState() {
        bTresPontosTimeA.isEnabled = isTimerRunning
        bDoisPontosTimeA.isEnabled = isTimerRunning
        bTLivreTimeA.isEnabled = isTimerRunning
        bTresPontosTimeB.isEnabled = isTimerRunning
        bDoisPontosTimeB.isEnabled = isTimerRunning
        bTLivreTimeB.isEnabled = isTimerRunning
    }

    fun adicionarPontos(pontos: Int, time: String) {
        if (!isTimerRunning) return

        if(time == "A") {
            pontuacaoTimeA += pontos
        } else {
            pontuacaoTimeB += pontos
        }

        atualizarPlacar(time)
    }

    fun atualizarPlacar(time: String) {
        if(time == "A") {
            pTimeA.text = pontuacaoTimeA.toString()
        } else {
            pTimeB.text = pontuacaoTimeB.toString()
        }
    }

    fun reiniciarPartida() {
        pontuacaoTimeA = 0
        pTimeA.text = pontuacaoTimeA.toString()
        pontuacaoTimeB = 0
        pTimeB.text = pontuacaoTimeB.toString()
        resetTimer()
        Toast.makeText(this, "Placar e cronômetro reiniciados", Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("pontuacaoTimeA", pontuacaoTimeA)
        outState.putInt("pontuacaoTimeB", pontuacaoTimeB)
        outState.putLong("timeLeftInMillis", timeLeftInMillis)
        outState.putBoolean("isTimerRunning", isTimerRunning)
        outState.putString("nomeTimeA", etTimeA.text.toString())
        outState.putString("nomeTimeB", etTimeB.text.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}