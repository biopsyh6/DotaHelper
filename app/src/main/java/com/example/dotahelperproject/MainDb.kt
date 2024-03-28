package com.example.dotahelperproject

import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Database
import com.example.dotahelperproject.entities.Attribute
import com.example.dotahelperproject.entities.Farm
import com.example.dotahelperproject.entities.Hero
import com.example.dotahelperproject.entities.Item
import com.example.dotahelperproject.entities.ItemCategory
import com.example.dotahelperproject.entities.Neutral
import com.example.dotahelperproject.entities.Role
import com.example.dotahelperproject.entities.RoleManual
import com.example.dotahelperproject.entities.Rune
import com.example.dotahelperproject.entities.Skill
import com.example.dotahelperproject.entities.Ward
import com.example.dotahelperproject.heroespage.model.room.HeroDao
import com.example.dotahelperproject.itemspage.model.room.ItemDao
import com.example.dotahelperproject.neutralspage.model.room.NeutralDao
import com.example.dotahelperproject.rolemanualpage.model.room.RolemanualDao
import com.example.dotahelperproject.runespage.model.room.RuneDao
import com.example.dotahelperproject.skillspage.model.room.SkillDao

@Database (entities = [Hero::class, Attribute::class, Farm::class, Item::class, ItemCategory::class,
Neutral::class, Role::class, RoleManual::class, Rune::class, Skill::class, Ward::class], version = 2)
abstract class MainDb : RoomDatabase() {
    abstract fun getDao(): Dao
    abstract fun heroDao(): HeroDao
    abstract fun itemDao(): ItemDao
    abstract fun neutralDao(): NeutralDao
    abstract fun rolemanualDao(): RolemanualDao
    abstract fun runeDao(): RuneDao
    abstract fun skillDao(): SkillDao

    companion object{
        fun getDb(context: Context): MainDb{
            return Room.databaseBuilder(
                context.applicationContext,
                MainDb::class.java,
                "dota.db"
            ).fallbackToDestructiveMigration()
                .build()
        }
    }
}