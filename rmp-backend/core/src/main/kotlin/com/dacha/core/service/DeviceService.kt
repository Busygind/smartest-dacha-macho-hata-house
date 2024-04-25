package com.dacha.core.service

import com.dacha.core.model.Device
import com.dacha.core.repo.DeviceRepository
import io.ktor.server.plugins.*
import java.util.UUID

class DeviceService(
    private val deviceRepository: DeviceRepository,
    private val roomService: RoomService,
    private val houseService: HouseService,
) {
    suspend fun getDevice(id: UUID): Device = deviceRepository.findDevice(id)
        ?: throw NotFoundException("Device with id=$id is not found")

    suspend fun saveDevice(device: Device, roomId: UUID? = null): Device =
        deviceRepository.saveDevice(device.also { it.roomId = roomId ?: it.roomId })

    suspend fun changeTriggerAmount(deviceId: UUID, amount: Int): Device =
        deviceRepository.changeTriggerAmount(deviceId, amount)

    suspend fun changeRoom(deviceId: UUID, roomId: UUID, houseId: UUID) {
        val house = houseService.getHouse(houseId)
        val targetRoom = roomService.getRoom(roomId)
        require(targetRoom.houseId == houseId) { "Specified room belongs to another house. Require house $houseId" }
        require(roomService.getRoom(getDevice(deviceId).roomId).houseId == houseId) { "Device belongs to another house. Require house $houseId" }
        deviceRepository.changeRoom(deviceId, roomId)
    }
}