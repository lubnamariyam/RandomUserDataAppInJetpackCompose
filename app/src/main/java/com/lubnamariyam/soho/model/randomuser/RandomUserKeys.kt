package com.lubnamariyam.soho.model.randomuser

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "random_user_keys")
data class RandomUserKeys(
    @PrimaryKey(autoGenerate = false)
    var id_keys: Int,
    val prevResult: Int,
    val nextResult: Int
)
