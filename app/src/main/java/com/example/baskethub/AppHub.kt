package com.example.baskethub

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class AppHub : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        applySavedOrDefaultTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hub)

        findViewById<View>(R.id.btn_scoreboard_app).setOnClickListener {
            startActivity(Intent(this, ScoreboardApp::class.java))
        }

        findViewById<View>(R.id.btn_calc_app).setOnClickListener {
            startActivity(Intent(this, CalculatorApp::class.java))
        }

        findViewById<View>(R.id.btn_games_app).setOnClickListener {
            startActivity(Intent(this, GamesHistoryApp::class.java))
        }

        findViewById<Button>(R.id.btn_toggle_theme).setOnClickListener {
            toggleTheme()
        }
    }

    private fun applySavedOrDefaultTheme() {
        val prefs: SharedPreferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
        val savedMode = prefs.getInt("night_mode", Int.MIN_VALUE)

        val modeToApply = if (savedMode == Int.MIN_VALUE) {
            // Primeiro acesso: define tema escuro como padrão
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            savedMode
        }

        AppCompatDelegate.setDefaultNightMode(modeToApply)

        if (savedMode == Int.MIN_VALUE) {
            prefs.edit().putInt("night_mode", modeToApply).apply()
        }
    }

    private fun toggleTheme() {
        val currentMode = AppCompatDelegate.getDefaultNightMode()
        val newMode = if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.MODE_NIGHT_NO
        } else {
            AppCompatDelegate.MODE_NIGHT_YES
        }
        AppCompatDelegate.setDefaultNightMode(newMode)
        val prefs: SharedPreferences = getSharedPreferences("theme_prefs", MODE_PRIVATE)
        prefs.edit().putInt("night_mode", newMode).apply()
        recreate()
    }

}
