package com.example.baskethub

import org.json.JSONObject

data class Match(
    val teamA: String,
    val teamB: String,
    val scoreA: Int,
    val scoreB: Int,
    val dateTime: String,
    val quarters: Int
) {
    constructor(json: JSONObject) : this(
        json.getString("teamA"),
        json.getString("teamB"),
        json.getInt("scoreA"),
        json.getInt("scoreB"),
        json.getString("dateTime"),
        json.getInt("quarters")
    )

    fun toJsonObject(): JSONObject {
        val json = JSONObject()
        json.put("teamA", teamA)
        json.put("teamB", teamB)
        json.put("scoreA", scoreA)
        json.put("scoreB", scoreB)
        json.put("dateTime", dateTime)
        json.put("quarters", quarters)
        return json
    }
}