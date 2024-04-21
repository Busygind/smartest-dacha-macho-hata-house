package com.dacha.core.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<NotFoundException> { call, cause ->
            call.respondText(text = "404: ${cause.message}" , status = HttpStatusCode.NotFound)
        }
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: ${cause.message}" , status = HttpStatusCode.InternalServerError)
        }
    }
}