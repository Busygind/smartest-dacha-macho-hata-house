package com.dacha.core.routing

import com.dacha.core.model.Device
import com.dacha.core.repo.DeviceRepository
import com.dacha.core.service.DeviceService
import com.dacha.core.util.toUuid
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private var deviceRepository = DeviceRepository()
private var deviceService = DeviceService(deviceRepository)

fun Route.devicesRoute() {

    route("/houses/{houseId}/rooms/{roomId}/devices") {
        get("/") {

        }

        post("/") {
            val deviceToSave = call.receive<Device>()
            call.respond(
                deviceService.saveDevice(
                    deviceToSave,
                    call.parameters["roomId"]!!.toUuid(),
                )
            )
        }

        get("/{deviceId}") {
            call.respond(
                deviceService.getDevice(
                    call.parameters["deviceId"]!!.toUuid(),
                )
            )
        }

        delete("/{deviceId}") {

        }

        put("/{deviceId}/trigger") {
            call.respond(
                deviceService.changeTriggerAmount(
                    call.parameters["deviceId"]!!.toUuid(),
                    call.request.queryParameters["amount"]!!.toInt()
                )
            )
        }
    }
    route("/houses/{houseId}/devices/{deviceId}/room/{roomId}") {
        patch("/") {

        }
    }
}