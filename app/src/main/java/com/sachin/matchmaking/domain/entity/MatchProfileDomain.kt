package com.sachin.matchmaking.domain.entity

data class MatchProfileDomain(
    val id: String,
    val gender: String,
    val email: String,
    val phone: String,
    val cell: String,
    val title: String,
    val first: String,
    val last: String,
    val date: String,
    val age: Int,
    val large: String,
    val medium: String,
    val thumbnail: String,
    val address : String
)

