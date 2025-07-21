package com.sachin.matchmaking.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "match_profile")
data class MatchProfileDbEntity(
    @PrimaryKey(autoGenerate = true)
    val dbId: Int,
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
    val address: String,
    var isProfileSelected: Boolean = false,
    var profileStatus: String = "",
)
