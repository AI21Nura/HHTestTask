package com.ainsln.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class OfferDTO(
    val id: String? = null,
    val title: String,
    val link: String,
    val button: ButtonDTO? = null
)

@Serializable
data class ButtonDTO(
    val text: String
)
