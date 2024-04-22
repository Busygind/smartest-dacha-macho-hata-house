package com.dacha.core.model.mappers

import com.dacha.core.model.Device
import com.dacha.core.model.DeviceType
import com.dacha.core.model.House
import com.dacha.core.model.Room
import com.dacha.core.repo.DeviceDAO
import com.dacha.core.repo.HouseDAO
import com.dacha.core.repo.RoomDAO
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
    triggerAmount = this[DeviceDAO.triggerAmount]
).also { it.roomId = this[DeviceDAO.roomId] }