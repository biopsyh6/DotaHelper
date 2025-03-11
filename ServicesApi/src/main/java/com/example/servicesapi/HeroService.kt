package com.example.servicesapi

import android.util.Log
import com.example.application.AttributeUseCases.firebase.AppFirebaseAttributeRepository
import com.example.application.HeroUseCases.firebase.AppFirebaseHeroRepository
import com.example.application.RoleUseCases.firebase.AppFirebaseRoleRepository
import com.example.application.dbInitializer.DbFirebaseInitializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale


//import khttp.get

class HeroService(
    private val attributeRepository: AppFirebaseAttributeRepository,
    private val roleRepository: AppFirebaseRoleRepository,
    private val heroRepository: AppFirebaseHeroRepository,
) {

    fun parseHeroData(data: String) {
        CoroutineScope(Dispatchers.IO).launch {
            //Attributes
//        val strength = Attribute(1, "Strength", R.drawable.hero_strength)
//        val agility = Attribute(2, "Agility", R.drawable.hero_agility)
//        val intelligence = Attribute(3, "Intelligence", R.drawable.hero_intelligence)
//        val universal = Attribute(4, "Universal", R.drawable.hero_universal)


            //Roles
//        val carry = Role(null, "Carry", R.drawable.filter_carry_icon)
//        val disabler = Role(null, "Disabler", R.drawable.filter_disabler_icon)
//        val durable = Role(null, "Durable", R.drawable.filter_durable_icon)
//        val escape = Role(null, "Escape", R.drawable.filter_escape_icon)
//        val initiator = Role(null, "Initiator", R.drawable.filter_initiator_icon)
//        val nuker = Role(null, "Nuker", R.drawable.filter_nuker_icon)
//        val pusher = Role(null, "Pusher", R.drawable.filter_pusher_icon)
//        val support = Role(null, "Support", R.drawable.filter_support_icon)


            val mainObject = JSONObject(data)
            val heroesArray = mainObject.getJSONObject("result").getJSONObject("data")
                .getJSONArray("heroes")
//        val dbInitializer = DbFirebaseInitializer(attributeRepository, roleRepository, heroRepository)
            for (i in 0 until heroesArray.length()) {
                val heroObject = heroesArray.getJSONObject(i)
                val id = heroObject.getInt("id") //id
                var primaryAttr = heroObject.getInt("primary_attr") //attribute
//            if (primaryAttr == 0){
//                primaryAttr = strength.attributeId
//            }
//            else if(primaryAttr == 1){
//                primaryAttr = agility.attributeId
//            }
//            else if(primaryAttr == 2){
//                primaryAttr = intelligence.attributeId
//            }
//            else if(primaryAttr == 3){
//                primaryAttr = universal.attributeId
//            }
                val roleLevels = heroObject.getJSONArray("role_levels") //roles
                val roleLevelsList = List(roleLevels.length()) { roleLevels.getInt(it) }
                val keys = listOf(
                    "Carry", "Support", "Nuker", "Disabler", "Jungler", "Durable",
                    "Escape", "Pusher", "Initiator"
                )
                val map = keys.zip(roleLevelsList).toMap()
                val roles = map.filter { it.value > 0 }.keys.toList()

                val name = heroObject.getString("name_loc") //name
                val health = heroObject.getInt("max_health") //health
                val mana = heroObject.getInt("max_mana") //mana
                val strength = heroObject.getInt("str_base") //strength
                val agility = heroObject.getInt("agi_base") //agility
                val intelligence = heroObject.getInt("int_base") //intelligence

                var damage_min = heroObject.getInt("damage_min")
                var damage_max = heroObject.getInt("damage_max")
                var damage: String = "$damage_min-$damage_max" //damage

                var armor = heroObject.getDouble("armor")
//            armor = String.format("%.1f", armor).toDouble() //armor
                val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.US))
                armor = df.format(armor).toDouble()
                var speed = heroObject.getInt("movement_speed") //speed
                var attack_range = heroObject.getInt("attack_range") //attack range
                var attack_speed = heroObject.getDouble("attack_rate") //attack speed
                var description = heroObject.getString("hype_loc") //short description
                description = description.replace("\\u003Cb\\u003E", "")
                description = description.replace("\\u003C/b\\u003E", "")
                description = description.replace("</b>", "")
                description = description.replace("<b>", "")
                var nameForUrl = name.lowercase()
                if (nameForUrl == "wraith king") {
                    nameForUrl = "skeleton_king"
                } else if (nameForUrl == "zeus") {
                    nameForUrl = "zuus"
                } else if (nameForUrl == "centaur warrunner") {
                    nameForUrl = "centaur"
                } else if (nameForUrl == "doom") {
                    nameForUrl = "doom_bringer"
                } else if (nameForUrl == "lifestealer") {
                    nameForUrl = "life_stealer"
                } else if (nameForUrl == "treant protector") {
                    nameForUrl = "treant"
                } else if (nameForUrl == "underlord") {
                    nameForUrl = "abyssal_underlord"
                } else if (nameForUrl == "anti-mage") {
                    nameForUrl = "antimage"
                } else if (nameForUrl == "shadow fiend") {
                    nameForUrl = "nevermore"
                } else if (nameForUrl == "timbersaw") {
                    nameForUrl = "shredder"
                } else if (nameForUrl == "nature's prophet") {
                    nameForUrl = "furion"
                } else if (nameForUrl == "necrophos") {
                    nameForUrl = "necrolyte"
                } else if (nameForUrl == "outworld destroyer") {
                    nameForUrl = "obsidian_destroyer"
                } else if (nameForUrl == "queen of pain") {
                    nameForUrl = "queenofpain"
                } else if (nameForUrl == "clockwerk") {
                    nameForUrl = "rattletrap"
                } else if (nameForUrl == "io") {
                    nameForUrl = "wisp"
                } else if (nameForUrl == "magnus") {
                    nameForUrl = "magnataur"
                } else if (nameForUrl == "vengeful spirit") {
                    nameForUrl = "vengefulspirit"
                } else if (nameForUrl == "windranger") {
                    nameForUrl = "windrunner"
                }

                nameForUrl = nameForUrl.replace(' ', '_')
                var imageUrl =
                    "https://cdn.cloudflare.steamstatic.com/apps/dota2/images/dota_react/heroes/$nameForUrl.png"

                DbFirebaseInitializer.heroInitialize(
                    primaryAttr,
                    roles,
                    name,
                    health,
                    mana,
                    strength,
                    agility,
                    intelligence,
                    damage,
                    armor,
                    speed,
                    attack_range,
                    attack_speed,
                    description,
                    imageUrl,
                    nameForUrl
                )

                ////////////////////////////

//            val retrofit = Retrofit.Builder()
//                .baseUrl("https://www.dota2.com/")
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .build()
//
//            val service = retrofit.create(MyApiService::class.java)
//            val call = service.fetchHeroData("english", 102)
//            call.enqueue(object : Callback<String> {
//                override fun onResponse(call: Call<String>, response: Response<String>) {
//                    if(response.isSuccessful){
//                        val json = response.body()
//                    }
//                    else {
//                        Log.d("Error", "${response.code()}")
//                    }
//                }
//
//                override fun onFailure(call: Call<String>, t: Throwable) {
//                    Log.d("Error", "${t.message}")
//                }
//            })

                /////////////////////////////
                val tag = "HERO"
                val message = "$name"
                Log.d(tag, message)
                Log.d("HERO", "$armor")


            }
//        val hero = Hero(
//            0,
//        )
        }
    }

}