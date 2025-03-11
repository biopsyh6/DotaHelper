package com.example.domain.entities

data class Test(
    val result: Result
) {
    data class Result(
        val `data`: Data,
        val status: Int // 1
    ) {
        data class Data(
            val heroes: List<Heroe>
        ) {
            data class Heroe(
                val abilities: List<Ability>,
                val agi_base: Int, // 18
                val agi_gain: Double, // 2.8
                val armor: Int, // 4
                val attack_capability: Int, // 1
                val attack_range: Int, // 150
                val attack_rate: Double, // 1.7
                val bio_loc: String, // Ulfsaar the Warrior is the fiercest member of an ursine tribe, protective of his land and his people. During the long winters, while the mothers sleep and nurse their cubs, the males patrol the lands above as tireless, vigilant defenders of their ancient ways. Hearing dim but growing rumors of a spreading evil, Ulfsaar headed out beyond the boundaries of his wild wooded homeland, intending to track down and destroy the threat at its source, before it could endanger his people. He is a proud creature with a bright strong spirit, utterly trustworthy, a staunch ally and defender.
                val complexity: Int, // 1
                val damage_max: Int, // 46
                val damage_min: Int, // 42
                val health_regen: Double, // 3.5
                val hype_loc: String, // <b>With each swipe of his claws</b>, Ursa increases his target's vulnerability to his next attack. Capable of briefly increasing how <b>swiftly</b> he can strike, and able to <b>slow down</b> nearby prey, he quickly tears apart his enemies.
                val id: Int, // 70
                val int_base: Int, // 16
                val int_gain: Double, // 1.5
                val magic_resistance: Int, // 25
                val mana_regen: Double, // 0.8
                val max_health: Int, // 670
                val max_mana: Int, // 267
                val movement_speed: Int, // 310
                val name: String, // npc_dota_hero_ursa
                val name_loc: String, // Ursa
                val npe_desc_loc: String, // Deals more damage with every hit to the same foe
                val order_id: Int, // 63
                val primary_attr: Int, // 1
                val projectile_speed: Int, // 900
                val role_levels: List<Int>,
                val sight_range_day: Int, // 1800
                val sight_range_night: Int, // 800
                val str_base: Int, // 25
                val str_gain: Double, // 2.6
                val talents: List<Talent>,
                val turn_rate: Double // 0.6
            ) {

//                fun toModel() = TestModel()

                data class Ability(
                    val ability_has_scepter: Boolean, // false
                    val ability_has_shard: Boolean, // true
                    val ability_is_granted_by_scepter: Boolean, // false
                    val ability_is_granted_by_shard: Boolean, // false
                    val behavior: String, // 134219780
                    val cast_points: List<Double>,
                    val cast_ranges: List<Int>,
                    val channel_times: List<Int>,
                    val cooldowns: List<Int>,
                    val damage: Int, // 2
                    val damages: List<Int>,
                    val desc_loc: String, // Ursa leaps forward %hop_distance% units and slams the earth, causing a powerful shock to damage and slow all enemy units in a nearby area for %abilityduration% seconds.
                    val dispellable: Int, // 2
                    val durations: List<Int>,
                    val flags: Int, // 0
                    val gold_costs: List<Any>,
                    val health_costs: List<Any>,
                    val id: Int, // 5357
                    val immunity: Int, // 4
                    val is_item: Boolean, // false
                    val item_cost: Int, // 0
                    val item_initial_charges: Int, // 0
                    val item_neutral_tier: Long, // 4294967295
                    val item_quality: Int, // 0
                    val item_stock_max: Int, // 0
                    val item_stock_time: Int, // 0
                    val lore_loc: String, // The very steps of a male ursine shake the ground as well as the resolve of opposing warriors.
                    val mana_costs: List<Int>,
                    val max_level: Int, // 4
                    val name: String, // ursa_earthshock
                    val name_loc: String, // Earthshock
                    val notes_loc: List<String>,
                    val scepter_loc: String,
                    val shard_loc: String, // Earthshock applies a %shard_enrage_duration% second Enrage on Ursa when cast.
                    val special_values: List<SpecialValue>,
                    val target_team: Int, // 0
                    val target_type: Int, // 0
                    val type: Int // 0
                ) {
                    data class SpecialValue(
                        val bonuses: List<Bonuse>,
                        val heading_loc: String, // RADIUS:
                        val is_percentage: Boolean, // false
                        val name: String, // shock_radius
                        val values_float: List<Double>,
                        val values_scepter: List<Any>,
                        val values_shard: List<Any>
                    ) {
                        data class Bonuse(
                            val name: String, // special_bonus_unique_ursa_2
                            val operation: Int, // 0
                            val value: Double // 0.25
                        )
                    }
                }

                data class Talent(
                    val ability_has_scepter: Boolean, // false
                    val ability_has_shard: Boolean, // false
                    val ability_is_granted_by_scepter: Boolean, // false
                    val ability_is_granted_by_shard: Boolean, // false
                    val behavior: String, // 2
                    val cast_points: List<Int>,
                    val cast_ranges: List<Int>,
                    val channel_times: List<Int>,
                    val cooldowns: List<Int>,
                    val damage: Int, // 0
                    val damages: List<Int>,
                    val desc_loc: String,
                    val dispellable: Int, // 0
                    val durations: List<Int>,
                    val flags: Int, // 0
                    val gold_costs: List<Any>,
                    val health_costs: List<Any>,
                    val id: Int, // 6975
                    val immunity: Int, // 0
                    val is_item: Boolean, // false
                    val item_cost: Int, // 0
                    val item_initial_charges: Int, // 0
                    val item_neutral_tier: Long, // 4294967295
                    val item_quality: Int, // 0
                    val item_stock_max: Int, // 0
                    val item_stock_time: Int, // 0
                    val lore_loc: String,
                    val mana_costs: List<Int>,
                    val max_level: Int, // 4
                    val name: String, // special_bonus_unique_ursa_4
                    val name_loc: String, // +{s:value}s Fury Swipes Reset Time
                    val notes_loc: List<Any>,
                    val scepter_loc: String,
                    val shard_loc: String,
                    val special_values: List<SpecialValue>,
                    val target_team: Int, // 0
                    val target_type: Int, // 0
                    val type: Int // 2
                ) {
                    data class SpecialValue(
                        val bonuses: List<Any>,
                        val heading_loc: String,
                        val is_percentage: Boolean, // false
                        val name: String, // value
                        val values_float: List<Double>,
                        val values_scepter: List<Any>,
                        val values_shard: List<Any>
                    )
                }
            }
        }
    }

//    fun toModel() =
//        this.result.data.heroes.map { it.toModel() }[0]


//    fun toModel(): TestModel{
//        val heroes = this.result.data.heroes
//        if(heroes.isNotEmpty()){
//            val firstHero = heroes[0]
//            return firstHero.toModel()
//        }
//        throw Exception("List of heroes is empty")
//    }
}