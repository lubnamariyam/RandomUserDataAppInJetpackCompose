package com.lubnamariyam.soho.pagging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.lubnamariyam.soho.database.RandomUserDatabase
import com.lubnamariyam.soho.model.randomuser.RandomUserKeys
import com.lubnamariyam.soho.model.randomuser.RandomUserResponse
import com.lubnamariyam.soho.network.RandomUserAPI
import javax.inject.Inject

@ExperimentalPagingApi
class RandomUserRemoteMediator @Inject constructor(
    private val randomUserApi: RandomUserAPI,
    private val randomUserDatabase: RandomUserDatabase
) : RemoteMediator<Int, RandomUserResponse>() {

    private val randomUserDao = randomUserDatabase.randomUserDao()
    private val randomUserRemoteKeysDao = randomUserDatabase.randomUserRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RandomUserResponse>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextResult?.minus(25) ?: 25
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevResult
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextResult
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = randomUserApi.getAllUsers(currentPage)
            val endOfPaginationReached = response.results.isEmpty()

            val prevPage = if (currentPage == 25) 0 else currentPage - 25
            val nextPage = if (endOfPaginationReached) 0 else currentPage + 25

            randomUserDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    randomUserDao.deleteAllRandomUsers()
                    randomUserRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.results.map { result ->
                    RandomUserKeys(
                        id_keys = response.id,
                        prevResult = prevPage,
                        nextResult = nextPage
                    )
                }
                randomUserRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                randomUserDao.addRandomUsers(response)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, RandomUserResponse>
    ): RandomUserKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                randomUserRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, RandomUserResponse>
    ): RandomUserKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                randomUserRemoteKeysDao.getRemoteKeys(id = unsplashImage.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, RandomUserResponse>
    ): RandomUserKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                randomUserRemoteKeysDao.getRemoteKeys(id = unsplashImage.id)
            }
    }
}