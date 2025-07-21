package com.sachin.matchmaking.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchProfileDao {

    @Query("SELECT * FROM match_profile ORDER BY id ASC")
    fun getPagedProfile(): PagingSource<Int, MatchProfileDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<MatchProfileDbEntity>)

    @Query("SELECT * FROM match_profile WHERE isProfileSelected == 1")
    fun observeAllProfile(): Flow<List<MatchProfileDbEntity>>

    @Query("UPDATE match_profile SET isProfileSelected = :isProfileSelected , profileStatus = :profileStatus WHERE id = :id")
    suspend fun updateProfile(id: String, isProfileSelected : Boolean , profileStatus : String)

    @Query("SELECT * FROM match_profile WHERE id IN (:ids)")
    suspend fun getProfilesByIds(ids: List<String>): List<MatchProfileDbEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM match_profile  WHERE isProfileSelected == 1)")
    fun isNotEmpty(): Flow<Boolean>

    @Query("DELETE FROM match_profile WHERE isProfileSelected == 0")
    suspend fun clearAllUnSelectedProfiles()
}