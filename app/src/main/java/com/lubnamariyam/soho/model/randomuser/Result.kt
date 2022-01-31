package com.lubnamariyam.soho.model.randomuser


import androidx.room.Embedded
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val cell: String,
    val email: String,
    val gender: String,

    @Embedded
    val location: Location,

    @Embedded
    val name: Name,

    val phone: String,

    @Embedded
    val picture: Picture,
) {
    constructor() : this(
        "", "", "", Location(Coordinates("", "")),
        Name("", ""), "", Picture("", "", "")
    )
}