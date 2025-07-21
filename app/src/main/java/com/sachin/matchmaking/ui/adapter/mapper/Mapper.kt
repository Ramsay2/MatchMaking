package com.sachin.matchmaking.ui.adapter.mapper

import com.sachin.matchmaking.data.db.MatchProfileDbEntity
import com.sachin.matchmaking.ui.adapter.entity.MatchProfileEntity


fun MatchProfileDbEntity.toMatchProfileEntity(): MatchProfileEntity {
    return MatchProfileEntity(
        dbId = dbId,
        id = if(id == "null") email else id,
        gender = gender,
        email = email,
        phone = phone,
        cell = cell,
        title = title,
        first = first,
        last = last,
        date = date,
        age = age,
        large = large,
        medium = medium,
        thumbnail = thumbnail,
        address = address,
        isProfileSelected = isProfileSelected,
        profileStatus = profileStatus,
        selectClicked = { },
        onDeclinedClicked = { }
    )
}