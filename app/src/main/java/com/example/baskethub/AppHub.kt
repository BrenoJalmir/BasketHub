package com.example.baskethub

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity

class AppHub : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hub)

        findViewById<View>(R.id.btn_scoreboard_app).setOnClickListener {
            startActivity(Intent(this, ScoreboardApp::class.java))
        }

        findViewById<View>(R.id.btn_calc_app).setOnClickListener {
            startActivity(Intent(this, CalculatorApp::class.java))
        }

        findViewById<View>(R.id.btn_games_app).setOnClickListener {
            startActivity(Intent(this, GamesApp::class.java))
        }
    }

}
