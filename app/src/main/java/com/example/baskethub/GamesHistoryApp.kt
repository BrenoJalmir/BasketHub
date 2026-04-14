package com.example.baskethub

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import java.io.File

class GamesHistoryApp : ComponentActivity() {
    private lateinit var rvMatches: RecyclerView
    private lateinit var adapter: MatchAdapter
    private val matchesList = mutableListOf<Match>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.games_history)

        rvMatches = findViewById(R.id.rvMatches)
        rvMatches.layoutManager = LinearLayoutManager(this)
        
        val fabAddMatch = findViewById<FloatingActionButton>(R.id.fabAddMatch)
        fabAddMatch.setOnClickListener {
            val intent = Intent(this, AddMatchActivity::class.java)
            startActivity(intent)
        }
        
        adapter = MatchAdapter(matchesList) { position ->
            deleteMatch(position)
        }
        rvMatches.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        loadMatches()
    }

    private fun loadMatches() {
        matchesList.clear()
        val file = File(filesDir, "matches.json")
        if (file.exists()) {
            val jsonString = file.readText()
            val jsonArray = JSONArray(jsonString)
            for (i in 0 until jsonArray.length()) {
                val matchJson = jsonArray.getJSONObject(i)
                matchesList.add(Match(matchJson))
            }
            // Mostrar os mais recentes primeiro
            matchesList.reverse()
        }
        adapter.notifyDataSetChanged()
    }

    private fun deleteMatch(position: Int) {
        matchesList.removeAt(position)
        saveAllMatches()
        adapter.notifyItemRemoved(position)
        Toast.makeText(this, "Partida excluída", Toast.LENGTH_SHORT).show()
    }

    private fun saveAllMatches() {
        val jsonArray = JSONArray()
        // Como o matchesList está invertido (mais recente primeiro), 
        // invertemos de volta antes de salvar para manter a ordem cronológica original no arquivo
        val listToSave = matchesList.toList().reversed()
        
        for (match in listToSave) {
            jsonArray.put(match.toJsonObject())
        }
        
        val file = File(filesDir, "matches.json")
        file.writeText(jsonArray.toString())
    }
}