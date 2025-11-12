package dev.yamh.domain.usecase.impl.core.util

public suspend fun <T> resultLauncher(request: suspend () -> T): Result<T> {
    return try {
        Result.success(request())
    } catch (e: Exception) {
        Result.failure(e)
    }
}