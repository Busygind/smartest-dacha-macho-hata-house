package com.dacha.core.repo

import com.dacha.core.model.Device
import com.dacha.core.model.mappers.toDeviceJson
import com.dacha.core.plugins.DatabaseManager.dbQuery
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import java.util.UUID

object DeviceDAO : Table("rooms_devices") {
    val id = uuid("id")
    val name = varchar("name", 64)
    val roomId = uuid("room_id").references(RoomDAO.id, onDelete = ReferenceOption.CASCADE)
    val type = varchar("type", 64)
    val state = bool("state")
    val triggerAmount = integer("trigger_amount").nullable()
}

class DeviceRepository {
    suspend fun findDevice(id: UUID): Device? = dbQuery {
        DeviceDAO.select {
            (DeviceDAO.id eq id)
        }.mapNotNull { it.toDeviceJson() }.singleOrNull()
    }

    suspend fun saveDevice(device: Device): Device {
        dbQuery {
            DeviceDAO.insert {
                it[id] = device.id
                it[name] = device.name
                it[roomId] = device.roomId
                it[type] = device.type.toString()
                it[state] = device.state
                it[triggerAmount] = device.triggerAmount
            }
        }
        return findDevice(device.id)!!
    }

    suspend fun changeTriggerAmount(deviceId: UUID, amount: Int): Device {
        dbQuery {
            DeviceDAO.update({ DeviceDAO.id eq deviceId }) {
                it[triggerAmount] = amount
            }
        }
        return findDevice(deviceId)!!
    }
}