package com.smartbulb.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform


