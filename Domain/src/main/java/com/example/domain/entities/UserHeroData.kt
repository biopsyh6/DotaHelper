package com.example.domain.entities

data class UserHeroData(
    val hero_id: Int,
    val last_played: Int,
    val games: Int,
    val win: Int,
    val with_games: Int,
    val with_win: Int,
    val against_games: Int,
    val against_win: Int
)
