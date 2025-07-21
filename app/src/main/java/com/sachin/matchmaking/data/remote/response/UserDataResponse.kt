package com.sachin.matchmaking.data.remote.response

import com.google.gson.annotations.SerializedName

data class MatchProfilesResponse(
    @SerializedName("results") val results: List<MatchProfile>,
    @SerializedName("info") val info: Info
)

data class MatchProfile(
    @SerializedName("gender") val gender: String,
    @SerializedName("name") val name: Name,
    @SerializedName("email") val email: String,
    @SerializedName("dob") val dob: Dob,
    @SerializedName("phone") val phone: String,
    @SerializedName("cell") val cell: String,
    @SerializedName("id") val id: Id,
    @SerializedName("picture") val picture: Picture,
    @SerializedName("location") val location: Location? = null
)

data class Name(
    @SerializedName("title") val title: String,
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String
)

data class Dob(
    @SerializedName("date") val date: String,
    @SerializedName("age") val age: Int
)

data class Id(
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: String
)

data class Picture(
    @SerializedName("large") val large: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("thumbnail") val thumbnail: String
)

data class Location(
    @SerializedName("street") val street: Street? = null,
    @SerializedName("city") val city: String? = null,
    @SerializedName("state") val state: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("postcode") val postcode: String? = null,
    @SerializedName("coordinates") val coordinates: Coordinates? = null,
    @SerializedName("timezone") val timezone: Timezone? = null
)

data class Street(
    @SerializedName("number") val number: Int? = null,
    @SerializedName("name") val name: String? = null
)

data class Coordinates(
    @SerializedName("latitude") val latitude: String? = null,
    @SerializedName("longitude") val longitude: String? = null
)

data class Timezone(
    @SerializedName("offset") val offset: String,
    @SerializedName("description") val description: String
)

data class Info(
    @SerializedName("seed") val seed: String,
    @SerializedName("results") val results: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("version") val version: String
)

