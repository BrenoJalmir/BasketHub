package com.example.baskethub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MatchAdapter(
    private val matches: MutableList<Match>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDateTime: TextView = itemView.findViewById(R.id.tvMatchDateTime)
        val tvTeamA: TextView = itemView.findViewById(R.id.tvTeamA)
        val tvTeamB: TextView = itemView.findViewById(R.id.tvTeamB)
        val tvScore: TextView = itemView.findViewById(R.id.tvScore)
        val tvQuarters: TextView = itemView.findViewById(R.id.tvQuarters)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDeleteMatch)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_match, parent, false)
        return MatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matches[position]
        holder.tvDateTime.text = match.dateTime
        holder.tvTeamA.text = match.teamA
        holder.tvTeamB.text = match.teamB
        holder.tvScore.text = "${match.scoreA} - ${match.scoreB}"
        holder.tvQuarters.text = "Quarters: ${match.quarters}"
        
        holder.btnDelete.setOnClickListener {
            onDeleteClick(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int = matches.size
}