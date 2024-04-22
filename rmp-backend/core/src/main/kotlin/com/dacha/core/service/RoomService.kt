package com.dacha.core.service

import com.dacha.core.model.Room
import com.dacha.core.repo.RoomRepository
import io.ktor.server.plugins.*
import java.util.UUID

class RoomService(
    private val repository: RoomRepository,
) {
    suspend fun getRoom(id: UUID): Room = repository.findRoom(id)
        ?: throw NotFoundException("Room with id=$id is not found")

    suspend fun saveRoom(room: Room, houseId: UUID? = null): Room =
        repository.saveRoom(room.also { it.houseId = houseId ?: it.houseId })

    suspend fun listRooms(houseId: UUID): List<Room> =
        repository.listRooms(houseId)
}