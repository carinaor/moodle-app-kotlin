package com.kconnector.moodle.src.async

class Promise<T>(private val onFullFill: ((T) -> Unit)? = null) {
    private val lock = Object()
    private var value: T? = null
    fun getValue(): T {
        return value!!
    }

    val isFulfilled: Boolean get() = value != null

    fun fulfill(value: T) {
        this.value = value
        synchronized(lock) { lock.notifyAll() }
        onFullFill?.let { it(value) }
    }

    @Throws(InterruptedException::class)
    fun waitForFulfillI(): T {
        synchronized(lock) {
            while (!isFulfilled) {
                lock.wait()
            }
        }
        return value!!
    }

    fun waitForFulfill(): T {
        synchronized(lock) {
            while (!isFulfilled) {
                try {
                    lock.wait()
                } catch (ignored: InterruptedException) {
                }
            }
        }
        return value!!
    }
}
