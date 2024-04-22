package com.dacha.core.repo

import com.dacha.core.model.House
import com.dacha.core.model.mappers.toHouseJson
import com.dacha.core.plugins.DatabaseManager.dbQuery
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.select
import java.util.UUID

object HouseDAO : Table("houses") {
    val id = uuid("id")
    val name = text("name")
    val userLogin = text("user_login")
    val deleted = bool("deleted")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}

class HouseRepository {

    suspend fun findHouse(id: UUID): House? = dbQuery {
            HouseDAO.select {
                (HouseDAO.id eq id)
            }.mapNotNull { it.toHouseJson() }.singleOrNull()
        }

    suspend fun saveHouse(house: House): House {
        dbQuery {
            HouseDAO.insert {
                it[id] = house.id
                it[name] = house.name
                it[userLogin] = house.userLogin
                it[createdAt] = house.createdAt
                it[updatedAt] = house.updatedAt
            }
        }
        return findHouse(house.id)!!
    }
}