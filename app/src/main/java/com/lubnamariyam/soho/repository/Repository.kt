package com.lubnamariyam.soho.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lubnamariyam.soho.database.RandomUserDatabase
import com.lubnamariyam.soho.model.randomuser.RandomUserResponse
import com.lubnamariyam.soho.network.RandomUserAPI
import com.lubnamariyam.soho.pagging.RandomUserRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class Repository @Inject constructor(
    private val randomUserApi: RandomUserAPI,
    private val randomUserDatabase: RandomUserDatabase
){
    fun getAllRandomUsers(): Flow<PagingData<RandomUserResponse>> {
        val pagingResourceFactory = {randomUserDatabase.randomUserDao().getAllRandomUsers()}
        val data = Pager(
            config = PagingConfig(25),
            remoteMediator = RandomUserRemoteMediator(
                randomUserApi = randomUserApi,
                randomUserDatabase = randomUserDatabase
            ),
            pagingSourceFactory = pagingResourceFactory
        ).flow
        return data
    }


}