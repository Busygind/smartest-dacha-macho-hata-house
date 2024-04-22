package com.dacha.core.routing

import com.dacha.core.model.Room
import com.dacha.core.repo.RoomRepository
import com.dacha.core.service.RoomService
import com.dacha.core.util.toUuid
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private var roomRepository = RoomRepository()
private var roomService = RoomService(roomRepository)

fun Route.roomsRoute() {

    route("/houses/{houseId}/rooms") {
        get("/{roomId}") {
            val room = roomService.getRoom(
                call.parameters["roomId"]!!.toUuid()
            )
            call.respond(room)
        }

        post("/") {
            val roomToSave = call.receive<Room>()
            call.respond(
                roomService.saveRoom(
                    roomToSave,
                    call.parameters["houseId"]!!.toUuid()
                )
            )
        }

        get("/") {
            call.respond(
                roomService.listRooms(call.parameters["houseId"]!!.toUuid())
            )
        }
    }
}