package com.example.dotahelperproject.heroespage.view.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.application.AttributeUseCases.firebase.AppFirebaseAttributeRepository
import com.example.application.HeroUseCases.firebase.AppFirebaseHeroRepository
import com.example.domain.abstractions.attribute.AttributeRepository
import com.example.domain.abstractions.hero.HeroRepository
import com.example.domain.entities.Hero
import com.example.dotahelperproject.R
import com.example.dotahelperproject.heroespage.view.HeroespageActivity
import com.squareup.picasso.Picasso

class IntelligenceFragment : Fragment() {
    private lateinit var gridLayout: GridLayout
    private lateinit var heroRepository: HeroRepository
    private lateinit var attributeRepository: AttributeRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_intelligence, container, false)
        gridLayout = view.findViewById(R.id.grid_layout_intelligence)

        heroRepository = AppFirebaseHeroRepository()
        attributeRepository = AppFirebaseAttributeRepository()
        val intelligence = "Intelligence"
        val intelligenceAttributeFirebaseId = attributeRepository.getAttributeFirebaseIdByName(intelligence)
        if(intelligenceAttributeFirebaseId != null) {
            heroRepository.getHeroesByAttributeFirebaseId(intelligenceAttributeFirebaseId).
                    observe(viewLifecycleOwner, { intelligenceHeroes ->
                        populateGridLayout(intelligenceHeroes)
                    })
        }
        return view
    }

    private fun populateGridLayout(heroes: List<Hero>){
        gridLayout.removeAllViews()
        for (hero in heroes) {
            val itemView = LayoutInflater.from(context).inflate(R.layout.item_hero_grid, gridLayout,
                false)
            val imageView: ImageView = itemView.findViewById(R.id.hero_image)
            Picasso.get().load(hero.imageUrl).into(imageView)

            itemView.setOnClickListener {
                (activity as? HeroespageActivity)?.openHeroDetailFragment(hero)
            }
            gridLayout.addView(itemView)
        }
    }
}