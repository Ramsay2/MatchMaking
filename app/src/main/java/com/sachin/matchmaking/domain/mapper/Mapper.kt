package com.sachin.matchmaking.domain.mapper

import com.sachin.matchmaking.data.db.MatchProfileDbEntity
import com.sachin.matchmaking.data.remote.response.MatchProfile
import com.sachin.matchmaking.domain.entity.MatchProfileDomain

fun MatchProfile.toDomain(): MatchProfileDomain {
    return MatchProfileDomain(
        id = "${id.name}${id.value}", // fallback if id is null or malformed
        gender = gender,
        email = email,
        phone = phone,
        cell = cell,
        title = name.title,
        first = name.first,
        last = name.last,
        date = dob.date,
        age = dob.age,
        large = picture.large,
        medium = picture.medium,
        thumbnail = picture.thumbnail,
        address = location?.street?.number.toString() + " " + location?.street?.name + "\n " + location?.city + ", " + location?.state + ", " + location?.country + ", " + location?.postcode
    )
}

fun MatchProfileDomain.toMatchProfileDbEntity(): MatchProfileDbEntity {
    return MatchProfileDbEntity(
        id = id,
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
        dbId = 0,
    )
}
