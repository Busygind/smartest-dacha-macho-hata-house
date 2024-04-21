package com.dacha.plugins

import com.dacha.dao.LogDao
import com.dacha.model.LogSaveRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting() {
    val logDao = LogDao(environment.config.property("storage.clickhouse.url").getString())

    routing {
        post("/log") {
            val logRequest = call.receive<LogSaveRequest>()
            logDao.saveLogEntity(logRequest)
            call.respond(HttpStatusCode.Created)
        }
    }
}
