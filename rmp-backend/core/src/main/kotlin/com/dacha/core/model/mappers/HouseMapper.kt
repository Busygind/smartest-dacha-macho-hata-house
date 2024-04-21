package com.dacha.core.model.mappers

import com.dacha.core.model.House
import com.dacha.core.repo.HouseDAO
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toJson(): House = House(
    id = this[HouseDAO.id],
    name = this[HouseDAO.name],
    userLogin = this[HouseDAO.userLogin],
    deleted = this[HouseDAO.deleted],
    createdAt = this[HouseDAO.createdAt],
    updatedAt = this[HouseDAO.updatedAt],
)