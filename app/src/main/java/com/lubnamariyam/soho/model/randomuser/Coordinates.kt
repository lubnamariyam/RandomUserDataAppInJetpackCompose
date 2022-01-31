package com.lubnamariyam.soho.model.randomuser

import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(
    val latitude: String = "",
    val longitude: String = ""
)