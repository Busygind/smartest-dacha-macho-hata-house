package com.dacha.core.repo

import com.dacha.core.model.Room
import com.dacha.core.model.mappers.toRoomJson
import com.dacha.core.plugins.DatabaseManager.dbQuery
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.util.UUID

object RoomDAO : Table("rooms") {
    val id = uuid("id")
    val houseId = uuid("house_id").references(HouseDAO.id, onDelete = ReferenceOption.CASCADE)
    val name = varchar("name", 64)
}

class RoomRepository {

    suspend fun findRoom(id: UUID): Room? = dbQuery {
        RoomDAO.select {
            (RoomDAO.id eq id)
        }.mapNotNull { it.toRoomJson() }.singleOrNull()
    }

    suspend fun saveRoom(room: Room): Room {
        dbQuery {
            RoomDAO.insert {
                it[id] = room.id
                it[name] = room.name
                it[houseId] = room.houseId
            }
        }
        return findRoom(room.id)!!
    }

    suspend fun listRooms(houseId: UUID): List<Room> =
        dbQuery {
            RoomDAO.select {
                (RoomDAO.houseId eq houseId)
            }.mapNotNull { it.toRoomJson() }
        }
}