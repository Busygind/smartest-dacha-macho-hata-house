package com.dacha.core.service

import com.dacha.core.model.House
import com.dacha.core.repo.HouseRepository
import io.ktor.server.plugins.*
import java.util.UUID

class HouseService(
    private val repository: HouseRepository,
) {
    suspend fun getHouse(id: UUID): House = repository.findHouse(id)
        ?: throw NotFoundException("House with id=$id is not found")

    suspend fun saveHouse(house: House): House = repository.saveHouse(house)
}