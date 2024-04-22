package com.dacha.core.model

import java.util.*

data class Device(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val type: DeviceType,
    val state: Boolean = false,
    val triggerAmount: Int? = null,
) {
    var roomId: UUID = UUID.randomUUID() // need to init from path variable
}