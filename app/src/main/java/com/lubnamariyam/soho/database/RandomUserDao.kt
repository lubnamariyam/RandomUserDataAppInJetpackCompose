package com.lubnamariyam.soho.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lubnamariyam.soho.model.randomuser.RandomUserResponse

@Dao
interface RandomUserDao {

    @Query("SELECT * FROM random_user_table")
    fun getAllRandomUsers(): PagingSource<Int, RandomUserResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRandomUsers(randomUsers: RandomUserResponse)

    @Query("DELETE FROM random_user_table")
    suspend fun deleteAllRandomUsers()

}