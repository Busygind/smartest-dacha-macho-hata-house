package com.dacha.core.routing

import com.dacha.core.model.House
import com.dacha.core.repo.HouseRepository
import com.dacha.core.service.HouseService
import com.dacha.core.util.toUuid
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

var repository = HouseRepository()
var service = HouseService(repository)

fun Route.housesRoute() {

    route("/houses") {
        get("/{houseId}") {
            val house = service.getHouse(call.parameters["houseId"]!!.toUuid())
            call.respond(house)
        }

        post("/") {
            val houseToSave = call.receive<House>()
            call.respond(service.saveHouse(houseToSave))
        }

        get("/{houseId}") {
//            val issueId = call.parameters["issueId"]
//                ?: throw IllegalArgumentException("Parameter issue Id not found")
//            issueRepo.get(issueId.toInt())?.let { issue -> call.respond(issue) }
        }
    }
}
