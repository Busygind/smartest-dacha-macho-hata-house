package com.dacha.core.listener

import com.dacha.core.repo.DeviceRepository
import com.dacha.model.EnvStateEvent
import org.redisson.api.RedissonReactiveClient

class EnvEventHandler(
    val redisClient: RedissonReactiveClient,
) {

    private val deviceRepository = DeviceRepository()

    suspend fun handle(event: EnvStateEvent) {
        val devices = deviceRepository.findDevicesInHome(event.houseId)
        for (device in devices) {
            // blah blah blah
        }

    }

}