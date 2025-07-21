package com.sachin.matchmaking.domain.usecase

import androidx.paging.PagingData
import com.sachin.matchmaking.data.db.MatchProfileDbEntity
import com.sachin.matchmaking.domain.repository.MatchProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMatchProfileUseCase @Inject constructor(
    private val repository: MatchProfileRepository
) {
    suspend fun invoke(gender : String): Flow<PagingData<MatchProfileDbEntity>> {
        return repository.getMatchingData(gender)
    }
}