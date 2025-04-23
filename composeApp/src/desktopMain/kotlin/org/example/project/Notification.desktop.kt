package org.example.project

internal actual fun notify(context: Any, message: String) {
    println("Desktop notification: $message")
}