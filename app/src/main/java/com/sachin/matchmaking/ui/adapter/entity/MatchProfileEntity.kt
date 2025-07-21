package com.sachin.matchmaking.ui.adapter.entity

data class MatchProfileEntity(
    val dbId : Int,
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
    var isProfileSelected : Boolean = false,
    var profileStatus : String = "Accepted",
    val selectClicked: (MatchProfileEntity) -> Unit,
    val onDeclinedClicked: (MatchProfileEntity) -> Unit,
) {

    fun onSelectClicked(profileEntity: MatchProfileEntity) {
        selectClicked.invoke(profileEntity)
    }

    fun onDeclinedClicked(profileEntity: MatchProfileEntity) {
        onDeclinedClicked.invoke(profileEntity)
    }
}

