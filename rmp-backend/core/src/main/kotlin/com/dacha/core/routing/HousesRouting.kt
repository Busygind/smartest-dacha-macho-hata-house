package com.dacha.core.routing

import com.dacha.core.model.House
import com.dacha.core.repo.HouseRepository
import com.dacha.core.service.HouseService
import com.dacha.core.util.toUuid
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private var houseRepository = HouseRepository()
private var houseService = HouseService(houseRepository)

fun Route.housesRoute() {

    route("/houses") {
        get("/{houseId}") {
            val house = houseService.getHouse(call.parameters["houseId"]!!.toUuid())
            call.respond(house)
        }

        post("/") {
            val houseToSave = call.receive<House>()
            call.respond(houseService.saveHouse(houseToSave))
        }

        get("/") {
            call.respondText("Not implemented")
            // todo получить дома по юзеру
        }
    }
}
