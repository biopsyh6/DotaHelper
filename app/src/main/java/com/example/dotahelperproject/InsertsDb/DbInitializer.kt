package com.example.dotahelperproject.InsertsDb

import com.example.dotahelperproject.MainDb
import com.example.dotahelperproject.R
import com.example.domain.entities.Attribute
import com.example.domain.entities.Role
import com.example.domain.entities.Rune
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DbInitializer(private val database: MainDb) {
    fun initialize(){
        CoroutineScope(Dispatchers.IO).launch {

            database.clearAllTables()

            val strength =
                Attribute(1, "Strength", R.drawable.hero_strength)
            val agility =
                Attribute(2, "Agility", R.drawable.hero_agility)
            val intelligence = Attribute(
                3,
                "Intelligence",
                R.drawable.hero_intelligence
            )
            val universal = Attribute(4, "Universal", R.drawable.hero_universal)


            val bounty_rune = Rune(
                null, "Bounty Rune", "Дает дополнительное золото.",
                R.drawable.rune_of_bounty_model
            )
            val water_rune = Rune(
                null, "Water Rune",
                "Мгновенно восстанавливает 40 здоровья и 80 маны.", R.drawable.rune_of_water_model
            )
            val damage_rune = Rune(
                null, "Amplify Damage Rune",
                "Увеличивает базовый урон на 80% и даёт +15% к урону от заклинаний.",
                R.drawable.rune_of_amplify_damage_model
            )
            val arcane_rune = Rune(
                null, "Arcane Rune",
                "Уменьшает время перезарядки способностей на 30% и расход маны на них на 30%.",
                R.drawable.rune_of_arcane_model
            )
            val wisdom_rune = Rune(
                null, "Wisdom Rune", "Даёт дополнительный опыт.",
                R.drawable.rune_of_wisdom_model
            )
            val haste_rune = Rune(
                null, "Haste Rune",
                "Увеличивает скорость передвижения до максимума.", R.drawable.rune_of_haste_model
            )
            val illusion_rune = Rune(
                null, "Illusion Rune",
                "Создает 2 иллюзии вашего героя, наносящие 35% урона. Иллюзии ближнего боя получают 200% урона, дальнего — 300%. Иллюзии пропадут через 75 секунд.",
                R.drawable.rune_of_illusion_model
            )
            val invisibility_rune = Rune(
                null, "Invisibility Rune",
                "Вы станете невидимым на 45 секунд. Руна прекращает свое действие при атаке или при использовании умения или предмета.",
                R.drawable.rune_of_invisibility_model
            )
            val regeneration_rune = Rune(
                null,
                "Regeneration Rune",
                "Восстанавливает здоровье и ману до максимума.",
                R.drawable.rune_of_regeneration_model
            )


            val carry = Role(null, "Carry", R.drawable.filter_carry_icon)
            val disabler = Role(null, "Disabler", R.drawable.filter_disabler_icon)
            val durable = Role(null, "Durable", R.drawable.filter_durable_icon)
            val escape = Role(null, "Escape", R.drawable.filter_escape_icon)
            val initiator = Role(null, "Initiator", R.drawable.filter_initiator_icon)
            val nuker = Role(null, "Nuker", R.drawable.filter_nuker_icon)
            val pusher = Role(null, "Pusher", R.drawable.filter_pusher_icon)
            val support = Role(null, "Support", R.drawable.filter_support_icon)

            val roles = listOf(carry, disabler, durable, escape, initiator, nuker, pusher, support)
            val runes = listOf(bounty_rune, water_rune, damage_rune, arcane_rune, wisdom_rune, haste_rune,
                illusion_rune, invisibility_rune, regeneration_rune)
            val attributes = listOf(strength, agility, intelligence, universal)
            database.runeDao().insertAll(runes)
            database.roleDao().insertAll(roles)
            database.attributeDao().insertAll(attributes)

        }
    }


}