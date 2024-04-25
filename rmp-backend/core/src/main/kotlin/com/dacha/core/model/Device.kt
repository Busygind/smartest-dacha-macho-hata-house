package com.dacha.core.model

import com.dacha.model.EnvType
import java.util.*

data class Device(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val type: DeviceType,
    val state: Boolean = false,
    val triggerAmount: Int? = null,
    val availableDeviceId: UUID,
    val eventType: EnvType,
) {
    var roomId: UUID = UUID.randomUUID() // need to init from path variable
}