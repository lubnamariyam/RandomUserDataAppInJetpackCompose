package com.lubnamariyam.soho.model.randomuser

import androidx.room.Embedded
import com.lubnamariyam.soho.model.randomuser.Coordinates
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @Embedded
    val coordinates: Coordinates,
)