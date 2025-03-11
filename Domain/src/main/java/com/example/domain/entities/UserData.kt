package com.example.domain.entities

data class UserData(
    val solo_competitive_rank: Int,
    val competitive_rank: Int,
    val rank_tier: Int,
    val leaderboard_rank: Int,
    val profile: UserProfile
)

data class UserProfile(
    val account_id: Int,
    val personaname: String,
    val name: String,
    val plus: Boolean,
    val cheese: Int,
    val steamid: String,
    val avatar: String,
    val avatarmedium: String,
    val avatarfull: String,
    val profileurl: String,
    val last_login: String,
    val loccountrycode: String,
    val is_contributor: Boolean,
    val is_subscriber: Boolean
)