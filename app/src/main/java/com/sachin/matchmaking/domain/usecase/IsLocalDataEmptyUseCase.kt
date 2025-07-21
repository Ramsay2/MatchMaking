package com.sachin.matchmaking.domain.usecase

import com.sachin.matchmaking.domain.repository.MatchProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsLocalDataEmptyUseCase @Inject constructor(val repository: MatchProfileRepository) {

    suspend fun invoke(): Flow<Boolean> {
        return repository.isLocalDataAvailable()
    }
}