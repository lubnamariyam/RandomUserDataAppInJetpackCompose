package com.lubnamariyam.soho.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lubnamariyam.soho.Utils.DataConverter
import com.lubnamariyam.soho.model.randomuser.RandomUserKeys
import com.lubnamariyam.soho.model.randomuser.RandomUserResponse

@TypeConverters(DataConverter::class)
@Database(entities = [RandomUserResponse::class, RandomUserKeys::class], version = 1, exportSchema = false)
abstract class RandomUserDatabase: RoomDatabase() {

    abstract fun randomUserDao() : RandomUserDao
    abstract fun randomUserRemoteKeysDao() : RandomUserRemoteKeysDao

}