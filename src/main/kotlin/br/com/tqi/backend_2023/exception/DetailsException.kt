package br.com.tqi.backend_2023.exception

import java.time.LocalDateTime

data class DetailsException(
    val title: String,
    val timestamp: LocalDateTime,
    val status: Int,
    val exception: String,
    val details: MutableMap<String, String?>
)
