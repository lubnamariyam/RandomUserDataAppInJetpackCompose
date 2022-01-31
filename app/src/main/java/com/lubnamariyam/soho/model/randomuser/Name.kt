package com.lubnamariyam.soho.model.randomuser

import kotlinx.serialization.Serializable

@Serializable

data class Name(
    val first: String = "",
    val last: String = "",
)