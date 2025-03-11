package com.example.application.dbInitializer

import android.util.Log
import com.example.application.AttributeUseCases.firebase.AppFirebaseAttributeRepository
import com.example.application.HeroUseCases.firebase.AppFirebaseHeroRepository
import com.example.application.ItemUseCases.firebase.AppFirebaseItemRepository
import com.example.application.RoleUseCases.firebase.AppFirebaseRoleRepository
import com.example.application.SkillUseCases.firebase.AppFirebaseSkillRepository
import com.example.domain.entities.Attribute
import com.example.domain.entities.Hero
import com.example.domain.entities.Item
import com.example.domain.entities.Role
import com.example.domain.entities.Skill
import com.example.domain.entities.SpecialValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DbFirebaseInitializer{

    private var isInitialized = false

    private val attributeRepository = AppFirebaseAttributeRepository()
    private val roleRepository = AppFirebaseRoleRepository()
    private val heroRepository = AppFirebaseHeroRepository()
    private val skillRepository = AppFirebaseSkillRepository()
    private val itemRepository = AppFirebaseItemRepository()

    lateinit var strengthAttr: Attribute
    lateinit var agilityAttr: Attribute
    lateinit var intelligenceAttr: Attribute
    lateinit var universalAttr: Attribute

    lateinit var  carry: Role
    lateinit var disabler: Role
    lateinit var durable: Role
    lateinit var escape: Role
    lateinit var initiator: Role
    lateinit var nuker: Role
    lateinit var pusher: Role
    lateinit var support: Role

    fun initializeAttributesAndRoles(){
//        if (isInitialized) return
        CoroutineScope(Dispatchers.IO).launch {
            //attributes initialize
            val allAttributes = attributeRepository.getAllAttributes().value
            if (allAttributes.isNullOrEmpty()){
                attributeRepository.clearAllAttributes()
                strengthAttr = Attribute(1, "Strength",
                    imageUrl = "https://cdn.cloudflare.steamstatic.com/apps/dota2/images/dota_react/icons/hero_strength.png")
                agilityAttr = Attribute(2, "Agility",
                    imageUrl = "https://cdn.cloudflare.steamstatic.com/apps/dota2/images/dota_react/icons/hero_agility.png")
                intelligenceAttr = Attribute(3, "Intelligence",
                    imageUrl = "https://cdn.cloudflare.steamstatic.com/apps/dota2/images/dota_react/icons/hero_intelligence.png")
                universalAttr = Attribute(4, "Universal",
                    imageUrl = "https://cdn.cloudflare.steamstatic.com/apps/dota2/images/dota_react/icons/hero_universal.png")
                val attributes = listOf(strengthAttr, agilityAttr, intelligenceAttr, universalAttr)
                attributes.forEach { attributeRepository.saveAttribute(it) }
            }
            //roles initialize
            val allRoles = roleRepository.getAllRoles().value
            if (allRoles.isNullOrEmpty()){
                roleRepository.clearAllRoles()
                carry = Role(null, "Carry",
                    imageUrl = "https://static.wikia.nocookie.net/dota2_gamepedia/images/8/81/Filter_carry_icon.png")
                disabler = Role(null, "Disabler",
                    imageUrl = "https://static.wikia.nocookie.net/dota2_gamepedia/images/0/04/Filter_disabler_icon.png")
                durable = Role(null, "Durable",
                    imageUrl = "https://static.wikia.nocookie.net/dota2_gamepedia/images/3/35/Filter_durable_icon.png")
                escape = Role(null, "Escape",
                    imageUrl = "https://static.wikia.nocookie.net/dota2_gamepedia/images/f/f2/Filter_escape_icon.png")
                initiator = Role(null, "Initiator",
                    imageUrl = "https://static.wikia.nocookie.net/dota2_gamepedia/images/6/62/Filter_initiator_icon.png")
                nuker = Role(null, "Nuker",
                    imageUrl = "https://static.wikia.nocookie.net/dota2_gamepedia/images/8/82/Filter_nuker_icon.png")
                pusher = Role(null, "Pusher",
                    imageUrl = "https://static.wikia.nocookie.net/dota2_gamepedia/images/d/d1/Filter_pusher_icon.png")
                support = Role(null, "Support",
                    imageUrl = "https://static.wikia.nocookie.net/dota2_gamepedia/images/1/1f/Filter_support_icon.png")
                val allRoles = listOf(carry, disabler, durable, escape, initiator, nuker, pusher, support)
                allRoles.forEach { roleRepository.saveRole(it) }
            }
            isInitialized = true
        }
    }
//    fun skillInitialize(skillName: String, heroName: String, description: String,
//                        imageUrl: String, damageType: Int, maxLevel: Int, abilityHasScepter: Boolean,
//                        abilityHasShard: Boolean, abilityIsGrantedByScepter: Boolean,
//                        abilityIsGrantedByShard: Boolean, specialValuesList: List<SpecialValue>,
//                        shardDescription: String, scepterDescription: String){
//        CoroutineScope(Dispatchers.IO).launch {
//            val heroRef = heroRepository.getHeroReferenceByName(heroName)
//            heroRef.addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    var damageTypeString: String = ""
//                    val hero = snapshot.getValue(Hero::class.java)
//                    val heroFbId = hero?.firebaseId ?: ""
//                    Log.d("HEROFIREBASE", "text: $heroFbId")
//                    if (damageType == 2) {
//                        damageTypeString = "Magical"
//                    } else if (damageType == 1) {
//                        damageTypeString = "Physical"
//                    } else if (damageType == 0) {
//                        damageTypeString = ""
//                    } else if (damageType == 4) {
//                        damageTypeString = "Pure"
//                    }
//                    val skill = Skill(
//                        heroId = heroFbId,
//                        skillName = skillName,
//                        heroName = heroName,
//                        description = description,
//                        imageUrl = imageUrl,
//                        damageType = damageTypeString,
//                        maxLevel = maxLevel,
//                        specialValues = specialValuesList,
//                        abilityHasScepter = abilityHasScepter,
//                        abilityHasShard = abilityHasShard,
//                        abilityIsGrantedByScepter = abilityIsGrantedByScepter,
//                        abilityIsGrantedByShard = abilityIsGrantedByShard,
//                        shardDescription = shardDescription,
//                        scepterDescription = scepterDescription
//                    )
//                    skillRepository.saveSkill(skill)
//                    Log.d("SAVING", "Save or no save skill")
//                }
//
//
//
//
//                override fun onCancelled(error: DatabaseError) {
//                    TODO("Not yet implemented")
//                }
//
//            })
//
//
//        }
//    }

    fun skillInitialize(skillName: String, heroName: String, description: String,
                        imageUrl: String, damageType: Int, maxLevel: Int, abilityHasScepter: Boolean,
                        abilityHasShard: Boolean, abilityIsGrantedByScepter: Boolean,
                        abilityIsGrantedByShard: Boolean, specialValuesList: List<SpecialValue>,
                        shardDescription: String, scepterDescription: String){
        CoroutineScope(Dispatchers.IO).launch {

//            var heroLiveData = heroRepository.getHeroByName(heroName)
//            heroLiveData.observeForever {hero ->
            var heroFbId = ""
            var damageTypeString = ""
//                heroFbId = hero?.firebaseId.toString()
            Log.d("HEROFIREBASE", "text: $heroFbId")
            if (damageType == 2) {
                damageTypeString = "Magical"
            } else if (damageType == 1) {
                damageTypeString = "Physical"
            } else if (damageType == 0) {
                damageTypeString = ""
            } else if (damageType == 4) {
                damageTypeString = "Pure"
            }
            val skill = Skill(
                heroId = heroFbId,
                skillName = skillName,
                heroName = heroName,
                description = description,
                imageUrl = imageUrl,
                damageType = damageTypeString,
                maxLevel = maxLevel,
                specialValues = specialValuesList,
                abilityHasScepter = abilityHasScepter,
                abilityHasShard = abilityHasShard,
                abilityIsGrantedByScepter = abilityIsGrantedByScepter,
                abilityIsGrantedByShard = abilityIsGrantedByShard,
                shardDescription = shardDescription,
                scepterDescription = scepterDescription
            )
            skillRepository.saveSkill(skill)
            Log.d("SAVING", "Save or no save skill")
        }


    }

    fun heroInitialize(primaryAttribute: Int, roles: List<String>, name:String,
                       health: Int, mana: Int, strength: Int, agility: Int,
                       intelligence: Int, damage: String, armor: Double,
                       speed: Int, attackRange: Int, attackSpeed: Double,
                       description: String, imageUrl: String, nameForUrl: String)
    {
        CoroutineScope(Dispatchers.IO).launch {
            //attributes initialize



//            attributeRepository.clearAllAttributes()
//            val strengthAttr = Attribute(1, "Strength",
//                imageUrl = "https://cdn.cloudflare.steamstatic.com/apps/dota2/images/dota_react/icons/hero_strength.png")
//            val agilityAttr = Attribute(2, "Agility",
//                imageUrl = "https://cdn.cloudflare.steamstatic.com/apps/dota2/images/dota_react/icons/hero_agility.png")
//            val intelligenceAttr = Attribute(3, "Intelligence",
//                imageUrl = "https://cdn.cloudflare.steamstatic.com/apps/dota2/images/dota_react/icons/hero_intelligence.png")
//            val universalAttr = Attribute(4, "Universal",
//                imageUrl = "https://cdn.cloudflare.steamstatic.com/apps/dota2/images/dota_react/icons/hero_universal.png")
//            val attributes = listOf(strengthAttr, agilityAttr, intelligenceAttr, universalAttr)
//            attributes.forEach { attributeRepository.saveAttribute(it) }

            //roles initialize
//            roleRepository.clearAllRoles()
//            val carry = Role(null, "Carry",
//                imageUrl = "https://static.wikia.nocookie.net/dota2_gamepedia/images/8/81/Filter_carry_icon.png")
//            val disabler = Role(null, "Disabler",
//                imageUrl = "https://static.wikia.nocookie.net/dota2_gamepedia/images/0/04/Filter_disabler_icon.png")
//            val durable = Role(null, "Durable",
//                imageUrl = "https://static.wikia.nocookie.net/dota2_gamepedia/images/3/35/Filter_durable_icon.png")
//            val escape = Role(null, "Escape",
//                imageUrl = "https://static.wikia.nocookie.net/dota2_gamepedia/images/f/f2/Filter_escape_icon.png")
//            val initiator = Role(null, "Initiator",
//                imageUrl = "https://static.wikia.nocookie.net/dota2_gamepedia/images/6/62/Filter_initiator_icon.png")
//            val nuker = Role(null, "Nuker",
//                imageUrl = "https://static.wikia.nocookie.net/dota2_gamepedia/images/8/82/Filter_nuker_icon.png")
//            val pusher = Role(null, "Pusher",
//                imageUrl = "https://static.wikia.nocookie.net/dota2_gamepedia/images/d/d1/Filter_pusher_icon.png")
//            val support = Role(null, "Support",
//                imageUrl = "https://static.wikia.nocookie.net/dota2_gamepedia/images/1/1f/Filter_support_icon.png")
//            val allRoles = listOf(carry, disabler, durable, escape, initiator, nuker, pusher, support)
//            allRoles.forEach { roleRepository.saveRole(it) }

            var primaryAttr: String = ""
            if (primaryAttribute == 0){
                primaryAttr = strengthAttr.firebaseId
            }
            else if(primaryAttribute == 1){
                primaryAttr = agilityAttr.firebaseId
            }
            else if(primaryAttribute == 2){
                primaryAttr = intelligenceAttr.firebaseId
            }
            else if(primaryAttribute == 3){
                primaryAttr = universalAttr.firebaseId
            }
            var finishRoles: MutableList<String> = mutableListOf()
            for(role in roles){
                if(role == "Carry"){
                    finishRoles.add(carry.firebaseId)
                }
                else if(role == "Support"){
                    finishRoles.add(support.firebaseId)
                }
                else if(role == "Nuker"){
                    finishRoles.add(nuker.firebaseId)
                }
                else if(role == "Disabler"){
                    finishRoles.add(disabler.firebaseId)
                }
                else if(role == "Durable"){
                    finishRoles.add(durable.firebaseId)
                }
                else if(role == "Escape"){
                    finishRoles.add(escape.firebaseId)
                }
                else if(role == "Pusher"){
                    finishRoles.add(pusher.firebaseId)
                }
                else if(role == "Initiator"){
                    finishRoles.add(initiator.firebaseId)
                }
            }
            val hero = Hero(attributeId = primaryAttr, roleIds = finishRoles, name = name,
                health = health, mana = mana, strength = strength, agility = agility,
                intelligence = intelligence, damage = damage, armor = armor, speed = speed,
                attackRange = attackRange, attackSpeed = attackSpeed, description = description,
                imageUrl = imageUrl, nameForUrl = nameForUrl)

            heroRepository.saveHero(hero)
        }
    }



    fun itemInitialize(items: List<Item>){
        CoroutineScope(Dispatchers.IO).launch {
            items.forEach { item ->
                itemRepository.saveItem(item)
            }
        }
    }
//    fun initialize(){
//        CoroutineScope(Dispatchers.IO).launch {
//            //attributes initialize
//            attributeRepository.clearAllAttributes()
//            val strength = Attribute(1, "Strength", R.drawable.hero_strength)
//            val agility = Attribute(2, "Agility", R.drawable.hero_agility)
//            val intelligence = Attribute(3, "Intelligence", R.drawable.hero_intelligence)
//            val universal = Attribute(4, "Universal", R.drawable.hero_universal)
//            val attributes = listOf(strength, agility, intelligence, universal)
//            attributes.forEach { attributeRepository.saveAttribute(it) }
//
//            //roles initialize
//            roleRepository.clearAllRoles()
//            val carry = Role(null, "Carry", R.drawable.filter_carry_icon)
//            val disabler = Role(null, "Disabler", R.drawable.filter_disabler_icon)
//            val durable = Role(null, "Durable", R.drawable.filter_durable_icon)
//            val escape = Role(null, "Escape", R.drawable.filter_escape_icon)
//            val initiator = Role(null, "Initiator", R.drawable.filter_initiator_icon)
//            val nuker = Role(null, "Nuker", R.drawable.filter_nuker_icon)
//            val pusher = Role(null, "Pusher", R.drawable.filter_pusher_icon)
//            val support = Role(null, "Support", R.drawable.filter_support_icon)
//            val roles = listOf(carry, disabler, durable, escape, initiator, nuker, pusher, support)
//            roles.forEach { roleRepository.saveRole(it) }
//
//
//            //heroes initialize
//            heroRepository.clearAllHeroes()
//            val Abaddon = Hero(0, universal.firebaseId, listOf(carry.firebaseId,
//                support.firebaseId, durable.firebaseId), "Abaddon", 604, 303,
//                22, 23, 19, "46-56", 3.8, 325, 150,
//                1.5, R.drawable.abaddon)
//            val Alchemist = Hero(0, strength.firebaseId, listOf(carry.firebaseId, support.firebaseId,
//                nuker.firebaseId, disabler.firebaseId, durable.firebaseId, initiator.firebaseId), "Alchemist",
//                626, 375, 23, 22, 25, "50-56",
//                3.7, 295, 150, 1.7, R.drawable.alchemist)
//
//            val heroes = listOf(Abaddon, Alchemist)
//            heroes.forEach { heroRepository.saveHero(it) }
//        }
//    }
}