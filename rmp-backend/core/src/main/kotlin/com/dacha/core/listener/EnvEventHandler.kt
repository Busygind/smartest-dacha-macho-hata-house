package com.dacha.core.listener

import com.dacha.core.repo.DeviceRepository
import com.dacha.model.EnvStateEvent

class EnvEventHandler {

    private val deviceRepository = DeviceRepository()

    suspend fun handle(event: EnvStateEvent) {
        val devices = deviceRepository.findDevicesInHome(event.houseId)
        for (device in devices) {
            if (device.eventType.name == event.envType) {
                continue
            }

            val triggerAmount: Int = device.triggerAmount ?: continue

            if ((event.amount >= triggerAmount && !device.state) || (event.amount < triggerAmount && device.state)) {
                deviceRepository.updateDeviceState(device.id, device.state.not())
            }
        }
    }
}
