package com.sachin.matchmaking.data.api_service

import com.sachin.matchmaking.data.response.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET
    suspend fun getUsers(
        @Query("page") page : Int,
        @Query("results") results : Int,
        @Query("gender") gender : String,
    ) : Response<ApiResponse>

}