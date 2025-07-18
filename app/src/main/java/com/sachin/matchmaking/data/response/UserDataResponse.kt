package com.sachin.matchmaking.data.response

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("results") val results: List<User>,
    @SerializedName("info") val info: Info
)

data class User(
    @SerializedName("gender") val gender: String,
    @SerializedName("name") val name: Name,
    @SerializedName("email") val email: String,
    @SerializedName("dob") val dob: Dob,
    @SerializedName("phone") val phone: String,
    @SerializedName("cell") val cell: String,
    @SerializedName("id") val id: Id,
    @SerializedName("picture") val picture: Picture
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

data class Info(
    @SerializedName("seed") val seed: String,
    @SerializedName("results") val results: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("version") val version: String
)

