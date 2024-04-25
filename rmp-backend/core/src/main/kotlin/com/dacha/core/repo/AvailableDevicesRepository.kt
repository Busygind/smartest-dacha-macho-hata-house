package com.dacha.core.repo

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table


object AvailableDeviceDao : Table("available_devices") {
    val id = uuid("id")
    val name = varchar("name", 64)
    val description = varchar("description", 256)
    val type = varchar("type", 64)
}


class AvailableDevicesRepository {

}