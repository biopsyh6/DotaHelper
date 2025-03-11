package com.example.dotahelperproject.heroespage.view.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.application.AttributeUseCases.firebase.AppFirebaseAttributeRepository
import com.example.application.HeroUseCases.firebase.AppFirebaseHeroRepository
import com.example.application.SkillUseCases.firebase.AppFirebaseSkillRepository
import com.example.domain.entities.Hero
import com.example.dotahelperproject.R
import com.example.dotahelperproject.heroespage.view.HeroespageActivity
import com.squareup.picasso.Picasso

class StrengthFragment : Fragment() {
    private lateinit var gridLayout: GridLayout
    private var heroRepository = AppFirebaseHeroRepository()
    private var attributeRepository = AppFirebaseAttributeRepository()
    private var skillRepository = AppFirebaseSkillRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("CREATING VIEW", "STRENGTH IS CREATED")
        val view = inflater.inflate(R.layout.fragment_strength, container, false)
        gridLayout = view.findViewById(R.id.grid_layout_strength)

        val strength = "Strength"
        val strengthAttributeFirebaseId = attributeRepository.getAttributeFirebaseIdByName(strength)
        if (strengthAttributeFirebaseId != null) {
            heroRepository.getHeroesByAttributeFirebaseId(strengthAttributeFirebaseId).
            observe(viewLifecycleOwner, { strengthHeroes ->
                Log.d("CHOPA", strengthHeroes.isEmpty().toString())
                populateGridLayout(strengthHeroes)
            })
        }

        return view
    }

    private fun populateGridLayout(heroes: List<Hero>){

        Log.d("POPULATE", "POPULATEGRIDLAYOUT")
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