package com.dacha.core.routing

import com.dacha.core.model.Device
import com.dacha.core.repo.DeviceRepository
import com.dacha.core.repo.HouseRepository
import com.dacha.core.repo.RoomRepository
import com.dacha.core.service.DeviceService
import com.dacha.core.service.HouseService
import com.dacha.core.service.RoomService
import com.dacha.core.util.toUuid
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private var deviceRepository = DeviceRepository()
private var roomRepository = RoomRepository()
private var roomService = RoomService(roomRepository)
private var houseRepository = HouseRepository()
private var houseService = HouseService(houseRepository)
private var deviceService = DeviceService(deviceRepository, roomService, houseService)

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
                    call.request.queryParameters["amount"]!!.toInt(),
                )
            )
        }

        put("/{deviceId}/status") {
            call.respond(
                deviceService.switchStatus(
                    call.parameters["deviceId"]!!.toUuid(),
                )
            )
        }
    }

    route("/houses/{houseId}/devices/{deviceId}/room/{roomId}") {
        patch("/") {
            deviceService.changeRoom(
                call.parameters["deviceId"]!!.toUuid(),
                call.parameters["roomId"]!!.toUuid(),
                call.parameters["houseId"]!!.toUuid(),
            )
            call.respond(HttpStatusCode.OK)
        }
    }
}