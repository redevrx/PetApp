package org.redev.rx.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform