package com.sachin.matchmaking.domain.repository

import androidx.paging.PagingData
import com.sachin.matchmaking.data.db.MatchProfileDbEntity
import kotlinx.coroutines.flow.Flow

interface MatchProfileRepository {

    suspend fun getMatchingData(gender : String) : Flow<PagingData<MatchProfileDbEntity>>

    suspend fun getSelectedProfiles() : Flow<List<MatchProfileDbEntity>>
    suspend fun updateProfile(profile : MatchProfileDbEntity)
    suspend fun isLocalDataAvailable() :Flow<Boolean>
}