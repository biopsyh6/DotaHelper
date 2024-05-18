package com.example.dotahelperproject

import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Database
import androidx.room.TypeConverters
import com.example.dotahelperproject.attribute.model.room.AttributeDao
import com.example.domain.entities.Attribute
import com.example.domain.entities.Farm
import com.example.domain.entities.Hero
import com.example.domain.entities.HeroRole
import com.example.domain.entities.Item
import com.example.domain.entities.ItemCategory
import com.example.domain.entities.Neutral
import com.example.domain.entities.Role
import com.example.domain.entities.RoleManual
import com.example.domain.entities.Rune
import com.example.domain.entities.Skill
import com.example.domain.entities.User
import com.example.domain.entities.Ward
import com.example.dotahelperproject.converters.Converters
import com.example.dotahelperproject.heroespage.model.room.HeroDao
import com.example.dotahelperproject.itemspage.model.room.ItemDao
import com.example.dotahelperproject.neutralspage.model.room.NeutralDao
import com.example.dotahelperproject.role.model.room.RoleDao
import com.example.dotahelperproject.rolemanualpage.model.room.RolemanualDao
import com.example.dotahelperproject.runespage.model.room.RuneDao
import com.example.dotahelperproject.skillspage.model.room.SkillDao
import com.example.dotahelperproject.user.model.room.UserDao

@Database (entities = [Hero::class, Attribute::class, Farm::class, Item::class, ItemCategory::class,
Neutral::class, Role::class, RoleManual::class, Rune::class, Skill::class, Ward::class, User::class,
                      HeroRole::class],
    version = 2)
@TypeConverters(Converters::class)
abstract class MainDb : RoomDatabase() {
    abstract fun getDao(): Dao
    abstract fun heroDao(): HeroDao
    abstract fun itemDao(): ItemDao
    abstract fun neutralDao(): NeutralDao
    abstract fun rolemanualDao(): RolemanualDao
    abstract fun runeDao(): RuneDao
    abstract fun skillDao(): SkillDao
    abstract fun roleDao(): RoleDao
    abstract fun attributeDao(): AttributeDao
    abstract fun userDao(): UserDao

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