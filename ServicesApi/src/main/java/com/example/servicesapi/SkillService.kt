package com.example.servicesapi

import android.util.Log
import com.example.application.HeroUseCases.firebase.AppFirebaseHeroRepository
import com.example.application.SkillUseCases.firebase.AppFirebaseSkillRepository
import com.example.application.dbInitializer.DbFirebaseInitializer
import com.example.domain.entities.SpecialValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class SkillService(
    private val skillRepository: AppFirebaseSkillRepository,
    private val heroRepository: AppFirebaseHeroRepository,
) {
    fun parseSkillData(data: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val mainObject = JSONObject(data)
            val heroesArray = mainObject.getJSONObject("result").getJSONObject("data")
                .getJSONArray("heroes")
            for (i in 0 until heroesArray.length()) {
                val heroObject = heroesArray.getJSONObject(i)
                val heroName = heroObject.getString("name_loc")
                var nameForUrl = heroName.lowercase()
                if (nameForUrl == "wraith king") {
                    nameForUrl = "skeleton_king"
                } else if (nameForUrl == "zeus") {
                    nameForUrl = "zuus"
                }
                nameForUrl = nameForUrl.replace(' ', '_')
                val abilitiesArray = heroObject.getJSONArray("abilities")
                for (j in 0 until abilitiesArray.length()) {
                    val abilityObject = abilitiesArray.getJSONObject(j)
                    val skillName = abilityObject.getString("name_loc") //skill name
                    var nameSkillForUrl = skillName.lowercase()
                    nameSkillForUrl = nameSkillForUrl.replace(' ', '_')
                    var imageUrl =
                        "https://cdn.cloudflare.steamstatic.com/apps/dota2/images/dota_react/abilities/${nameForUrl}_${nameSkillForUrl}.png"
                    val description = abilityObject.getString("desc_loc")
                    val damageType = abilityObject.getInt("damage")
                    val maxLevel = abilityObject.getInt("max_level")
                    val shardDescription = abilityObject.getString("shard_loc")
                    val scepterDescription = abilityObject.getString("scepter_loc")
                    val abilityHasScepter = abilityObject.getBoolean("ability_has_scepter")
                    val abilityHasShard = abilityObject.getBoolean("ability_has_shard")
                    val abilityIsGrantedByScepter =
                        abilityObject.getBoolean("ability_is_granted_by_scepter")
                    val abilityIsGrantedByShard =
                        abilityObject.getBoolean("ability_is_granted_by_shard")
                    val specialValuesArray = abilityObject.getJSONArray("special_values")
                    val specialValuesList = mutableListOf<SpecialValue>()
                    for (k in 0 until specialValuesArray.length()) {
                        val specialValueObject = specialValuesArray.getJSONObject(k)
                        val specialValue = SpecialValue(
                            name = specialValueObject.getString("name"),
                            values = specialValueObject.getJSONArray("values_float").toList(),
                            isPercentage = specialValueObject.getBoolean("is_percentage"),
                            heading = specialValueObject.optString("heading_loc", ""),
                        )
                        specialValuesList.add(specialValue)


//                    val specialValueMap = mutableMapOf<String, Any>()
//                    val keys = specialValueObject.keys()
//                    while(keys.hasNext()){
//                        val key = keys.next()
//                        specialValueMap[key] = specialValueObject.get(key)
//                    }
//                    specialValuesList.add(specialValueMap)
                    }

                    DbFirebaseInitializer.skillInitialize(
                        skillName,
                        heroName,
                        description,
                        imageUrl,
                        damageType,
                        maxLevel,
                        abilityHasScepter,
                        abilityHasShard,
                        abilityIsGrantedByScepter,
                        abilityIsGrantedByShard,
                        specialValuesList,
                        shardDescription,
                        scepterDescription
                    )


                    val tag = "SKILL"
                    val message = "$skillName"
                    Log.d(tag, message)
                    Log.d("SKILL", "${specialValuesList[0]}")

                }
            }
        }
    }

    private fun JSONArray.toList(): List<Float> {
        val list = mutableListOf<Float>()
        for(i in 0 until length()){
            list.add(getDouble(i).toFloat())
        }
        return list
    }
}