package com.example.dotahelperproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.domain.entities.HeroData
import com.example.domain.entities.MatchData
import com.example.dotahelperproject.R

class MatchesAdapter(context: Context, private val matches: List<MatchData>, private val heroMap: Map<Int, HeroData>)
    : ArrayAdapter<MatchData>(context, 0, matches){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_match, parent, false)
        }
        val match = getItem(position)
        val hero = heroMap[match?.hero_id]

        val radiantWinTextView = view!!.findViewById<TextView>(R.id.winTextView)
        val heroNameTextView = view.findViewById<TextView>(R.id.heroNameTextView)

        radiantWinTextView.text = if (match!!.radiant_win!!) "Radiant Win" else "Dire Win"
        heroNameTextView.text = hero?.localized_name ?: "Unknown Hero"
        return view
    }
}