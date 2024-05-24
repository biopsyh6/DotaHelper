package com.example.dotahelperproject.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dotahelperproject.heroespage.view.screens.AgilityFragment
import com.example.dotahelperproject.heroespage.view.screens.IntelligenceFragment
import com.example.dotahelperproject.heroespage.view.screens.StrengthFragment
import com.example.dotahelperproject.heroespage.view.screens.UniversalFragment

class NumberAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
//        val fragment = NumberFragment()
//        fragment.arguments = Bundle().apply {
//            putInt(ARG_OBJECT, position + 1)
//        }
//        return fragment
        return when(position){
            0 -> StrengthFragment()
            1 -> AgilityFragment()
            2 -> IntelligenceFragment()
            else -> UniversalFragment()
        }
    }
}