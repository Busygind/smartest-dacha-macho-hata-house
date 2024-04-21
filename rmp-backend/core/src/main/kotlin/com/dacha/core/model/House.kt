package com.dacha.core.model

import java.time.LocalDateTime
import java.util.UUID

data class House(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val userLogin: String,
    val deleted: Boolean = false,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)