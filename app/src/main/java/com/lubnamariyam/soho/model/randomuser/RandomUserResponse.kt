package com.lubnamariyam.soho.model.randomuser

import androidx.room.*
import com.google.gson.internal.LinkedTreeMap
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "random_user_table")
data class RandomUserResponse(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    var results: List<Result>
){
    constructor() : this(0, listOf())
}