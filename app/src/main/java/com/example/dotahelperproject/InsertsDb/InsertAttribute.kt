package com.example.dotahelperproject.InsertsDb

import com.example.dotahelperproject.MainDb
import com.example.dotahelperproject.R
import com.example.dotahelperproject.entities.Attribute


abstract class InsertAttribute {
    fun InsertAttribute(){
        val strength = Attribute(null, "Strength", R.drawable.strength_attribute)
        val dexterity = Attribute(null, "Dexterity", R.drawable.dexterity_attribute)
        val intelligence = Attribute(null, "Intelligence", R.drawable.intelligence_attribute)
//        val db = MainDb.getDb(this)
    }
}