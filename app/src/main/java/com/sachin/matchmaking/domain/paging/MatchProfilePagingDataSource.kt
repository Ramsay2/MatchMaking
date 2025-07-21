package com.sachin.matchmaking.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sachin.matchmaking.data.db.AppDatabase
import com.sachin.matchmaking.data.db.MatchProfileDbEntity
import com.sachin.matchmaking.data.remote.api_service.ApiService
import com.sachin.matchmaking.domain.mapper.toDomain
import com.sachin.matchmaking.domain.mapper.toMatchProfileDbEntity

class MatchProfilePagingDataSource(
    private val api: ApiService,
    private val db: AppDatabase,
    val result: Int = 20,
    val gender: String
) : PagingSource<Int, MatchProfileDbEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MatchProfileDbEntity> {
        val page = params.key ?: 1
        return try {
            val response = api.getProfiles(page, result, gender).results.map { it.toDomain() }

            val localProfilesMap = db.matchProfileDao().getProfilesByIds(response.map { it.id })
                .associateBy { it.id }

            val finalList = response.map { remoteItem ->
                localProfilesMap[remoteItem.id] ?: remoteItem.toMatchProfileDbEntity()
            }

            if (page == 1) {
                db.matchProfileDao().clearAllUnSelectedProfiles()
            }

            db.matchProfileDao().insertAll(finalList)

            LoadResult.Page(
                data = response.map { it.toMatchProfileDbEntity() },
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )

        } catch (e: Exception) {
            try {
                db.matchProfileDao().getPagedProfile().load(params)
            } catch (e: Exception) {
                LoadResult.Error(e)
            }

        }
    }

    override fun getRefreshKey(state: PagingState<Int, MatchProfileDbEntity>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}