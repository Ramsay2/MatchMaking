package com.sachin.matchmaking.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sachin.matchmaking.data.db.AppDatabase
import com.sachin.matchmaking.data.db.MatchProfileDbEntity
import com.sachin.matchmaking.data.remote.api_service.ApiService
import com.sachin.matchmaking.domain.paging.MatchProfilePagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MatchProfileRepositoryImpl @Inject constructor(
    val apiService: ApiService,
    private val db: AppDatabase
) : MatchProfileRepository {

    override suspend fun getMatchingData(gender: String): Flow<PagingData<MatchProfileDbEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                MatchProfilePagingDataSource(
                    apiService,
                    gender = gender,
                    db = db
                )
            }
        ).flow
    }

    override suspend fun getSelectedProfiles(): Flow<List<MatchProfileDbEntity>> {
        return db.matchProfileDao().observeAllProfile()
    }

    override suspend fun updateProfile(profile: MatchProfileDbEntity) {
        db.matchProfileDao().updateProfile(profile.id , profile.isProfileSelected , profile.profileStatus)
    }

    override suspend fun isLocalDataAvailable(): Flow<Boolean> {
        return db.matchProfileDao().isNotEmpty()
    }
}