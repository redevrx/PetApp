package org.redev.rx.app.utils

import io.ktor.utils.io.core.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


fun <T> StateFlow<T>.asFlowC() = FlowC(this)


class FlowC<T>(private val origin: StateFlow<T>) : StateFlow<T> by origin {
    private val scope = MainScope()
    private var job: Job? = null

    fun listen(block: (event: T) -> Unit): FlowC<T> {
        job = onEach(block).launchIn(scope)

        return this
    }

    private val closeable = object : Closeable {
        override fun close() {
            job?.cancel()
        }
    }

    fun close() = closeable.close()
}
