package com.sachin.matchmaking.domain.paging

//
//@OptIn(ExperimentalPagingApi::class)
//class RoomMediatorDataSource(
//    private val api: ApiService,
//    val database: AppDatabase,
//    val result: Int = 20,
//    val gender: String
//) : RemoteMediator<Int, MatchProfileDbEntity>() {
//
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, MatchProfileDbEntity>
//    ): MediatorResult {
//
//        val page = when (loadType) {
//            LoadType.REFRESH -> 1
//            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
//            LoadType.APPEND -> state.pages.sumOf { it.data.size }
//        }
//
//        return try {
//            val profiles = api.getProfiles(page, result, gender)
//            val dbEntity = profiles.results.map { it.toDomain().toMatchProfileDbEntity() }
//
//            database.withTransaction {
//                if (loadType == LoadType.REFRESH) {
//                    database.matchProfileDao().clearAll()
//                }
//                database.matchProfileDao().insertAll(dbEntity)
//            }
//            MediatorResult.Success(endOfPaginationReached = dbEntity.isEmpty())
//
//        } catch (e: Exception) {
//            MediatorResult.Error(e)
//        }
//    }
//}