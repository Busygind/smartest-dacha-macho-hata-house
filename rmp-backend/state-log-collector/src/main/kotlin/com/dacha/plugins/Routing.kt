package com.dacha.plugins

import com.dacha.dao.LogDao
import com.dacha.model.LogSaveRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config

lateinit var client: RedissonClient

fun Application.configureRouting() {
    val topic = environment.config.property("storage.redis.topic").getString()

    routing {
        post("/log") {
            client.getTopic(topic).publish(call.receive<LogSaveRequest>())
            call.respond(HttpStatusCode.Created)
        }
    }
}

fun Application.configureRedis() {
    val logDao = LogDao(environment.config.property("storage.clickhouse.url").getString())

    val config = Config()
    config.useSingleServer().address = environment.config.property("storage.redis.url").getString()
    client = Redisson.create(config)

    val topic = environment.config.property("storage.redis.topic").getString()

    client.getTopic(topic).addListener(LogSaveRequest::class.java) { _, msg ->
        runBlocking {
            logDao.saveLogEntity(msg)
        }
    }
}
