package ru.arckontyr.rampractice.domain.extensions

import kotlin.coroutines.cancellation.CancellationException

inline fun <T> runSuspendCatching(block: () -> T): Result<T> =
    try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        Result.failure(e)
    }