package com.example.dotahelperproject.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dotahelperproject.itemspage.view.screens.ItemFragment
import com.example.dotahelperproject.itemspage.view.screens.NeutralItemFragment

class NumberItemAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
//        return when(position){
//            0 -> ItemFragment()
//            else -> ItemFragment()
//        }
        return when(position){
            0 -> ItemFragment()
            else -> NeutralItemFragment()
        }
    }
}