package com.smartbulb.server

import com.smartbulb.shared.Greeting
import com.smartbulb.shared.SERVER_PORT
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {

    val greeting = Greeting().greet()

    routing {
        get("/") {
            call.respondText("Ktor: ${greeting}")
        }
    }
}