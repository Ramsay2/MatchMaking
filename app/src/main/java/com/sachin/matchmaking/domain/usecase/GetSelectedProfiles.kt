package com.sachin.matchmaking.domain.usecase

import com.sachin.matchmaking.data.db.MatchProfileDbEntity
import com.sachin.matchmaking.domain.repository.MatchProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSelectedProfiles@Inject constructor(
    private val repository: MatchProfileRepository
) {

    suspend fun invoke() : Flow<List<MatchProfileDbEntity>>{
        return repository.getSelectedProfiles()
    }
}