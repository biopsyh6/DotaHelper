package com.example.domain.entities

data class MatchData(
    val match_id: Int,
    val player_slot: Int?,
    val radiant_win: Boolean?,
    val duration: Int,
    val game_mode: Int,
    val lobby_type: Int,
    val hero_id: Int,
    val start_time: Int,
    val version: Int?,
    val kills: Int,
    val deaths: Int,
    val assists: Int,
//    val skill: Int?,
    val average_rank: Int?,
    val leaver_status: Int,
    val party_size: Int?
)
