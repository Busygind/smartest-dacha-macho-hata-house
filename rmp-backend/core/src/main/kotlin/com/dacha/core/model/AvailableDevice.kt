package com.dacha.core.model

import java.util.UUID

data class AvailableDevice(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val type: DeviceType,
    val description: String? = null,
)