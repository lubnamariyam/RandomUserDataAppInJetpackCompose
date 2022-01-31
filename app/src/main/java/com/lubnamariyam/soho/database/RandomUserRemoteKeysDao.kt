package com.lubnamariyam.soho.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lubnamariyam.soho.model.randomuser.RandomUserKeys

@Dao
interface RandomUserRemoteKeysDao {

    @Query("SELECT * FROM random_user_keys WHERE id_keys =:id")
    suspend fun getRemoteKeys(id: Int): RandomUserKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<RandomUserKeys>)

    @Query("DELETE FROM random_user_keys")
    suspend fun deleteAllRemoteKeys()

}