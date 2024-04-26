package com.dacha.core.service

import com.dacha.core.listener.LOG_EVENT_QUEUE
import com.dacha.core.listener.USER_LOGIN_STUB
import com.dacha.core.listener.redisClient
import com.dacha.core.model.Device
import com.dacha.core.repo.DeviceRepository
import com.dacha.model.LogSaveRequest
import io.ktor.server.plugins.*
import kotlinx.coroutines.reactor.awaitSingle
import java.time.Instant
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

    suspend fun switchStatus(deviceId: UUID) {
        val device = deviceRepository.findDevice(deviceId)
        require(device != null) { "Device by id = $deviceId not found" }
        val room = roomService.getRoom(device.id)
        val newState = device.state.not()
        deviceRepository.saveDevice(device.copy(state = newState))

        redisClient.getTopic(LOG_EVENT_QUEUE).publish(
            LogSaveRequest(
                userLogin = USER_LOGIN_STUB,
                logTime = Instant.now(),
                houseId = room.houseId,
                roomId = room.id,
                deviceId = device.id,
                newState = newState,
                message = "Changed device state from ${newState.not()} to $newState"
            )
        ).awaitSingle()
    }
}