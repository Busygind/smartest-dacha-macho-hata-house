package com.dacha.core

import com.dacha.core.plugins.configureSerialization
import com.dacha.core.routing.housesRoute
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureSerialization()
    initDB()

    install(Routing) {
        housesRoute()
    }
}

fun initDB() {
    val config = HikariConfig("/hikari.properties")
    val ds = HikariDataSource(config)
    Database.connect(ds)
}
