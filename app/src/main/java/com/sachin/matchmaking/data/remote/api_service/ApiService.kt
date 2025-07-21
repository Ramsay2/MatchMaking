package com.sachin.matchmaking.data.remote.api_service

import com.sachin.matchmaking.data.remote.response.MatchProfilesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    suspend fun getProfiles(
        @Query("page") page : Int,
        @Query("results") results : Int,
        @Query("gender") gender : String,
    ) :MatchProfilesResponse

}