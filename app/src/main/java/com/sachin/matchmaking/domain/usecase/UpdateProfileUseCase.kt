package com.sachin.matchmaking.domain.usecase

import com.sachin.matchmaking.data.db.MatchProfileDbEntity
import com.sachin.matchmaking.domain.repository.MatchProfileRepository
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    val repository: MatchProfileRepository
) {

    suspend fun invoke(profile: MatchProfileDbEntity){
        repository.updateProfile(profile)
    }
}