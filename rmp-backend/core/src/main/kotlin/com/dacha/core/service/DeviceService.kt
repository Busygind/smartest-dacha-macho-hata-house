package com.dacha.core.service

import com.dacha.core.model.Device
import com.dacha.core.repo.DeviceRepository
import io.ktor.server.plugins.*
import java.util.UUID

class DeviceService(
    private val repository: DeviceRepository,
) {
    suspend fun getDevice(id: UUID): Device = repository.findDevice(id)
        ?: throw NotFoundException("Device with id=$id is not found")

    suspend fun saveDevice(device: Device, roomId: UUID? = null): Device =
        repository.saveDevice(device.also { it.roomId = roomId ?: it.roomId })

    suspend fun changeTriggerAmount(deviceId: UUID, amount: Int): Device =
        repository.changeTriggerAmount(deviceId, amount)
}