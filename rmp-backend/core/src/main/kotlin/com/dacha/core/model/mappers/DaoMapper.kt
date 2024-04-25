package com.dacha.core.model.mappers

import com.dacha.core.model.*
import com.dacha.core.repo.AvailableDeviceDao
import com.dacha.core.repo.DeviceDAO
import com.dacha.core.repo.HouseDAO
import com.dacha.core.repo.RoomDAO
import com.dacha.model.EnvType
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toHouseJson(): House = House(
    id = this[HouseDAO.id],
    name = this[HouseDAO.name],
    userLogin = this[HouseDAO.userLogin],
    deleted = this[HouseDAO.deleted],
    createdAt = this[HouseDAO.createdAt],
    updatedAt = this[HouseDAO.updatedAt],
)

fun ResultRow.toRoomJson(): Room = Room(
    id = this[RoomDAO.id],
    name = this[RoomDAO.name]
).also { it.houseId = this[RoomDAO.houseId] }

fun ResultRow.toDeviceJson(): Device = Device(
    id = this[DeviceDAO.id],
    name = this[DeviceDAO.name],
    type = DeviceType.valueOf(this[DeviceDAO.type]),
    state = this[DeviceDAO.state],
    triggerAmount = this[DeviceDAO.triggerAmount],
    availableDeviceId = this[DeviceDAO.availableDeviceId],
    eventType = EnvType.valueOf(this[DeviceDAO.eventType])
).also { it.roomId = this[DeviceDAO.roomId] }

fun ResultRow.toAvailableDevice(): AvailableDevice = AvailableDevice(
    id = this[AvailableDeviceDao.id],
    name = this[AvailableDeviceDao.name],
    description = this[AvailableDeviceDao.description],
    type = DeviceType.valueOf(this[AvailableDeviceDao.type])
)