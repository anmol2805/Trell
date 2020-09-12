package com.anmol2805.base.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Executes business logic synchronously or asynchronously using Coroutines.
 *
 * The [execute] method of [SuspendUseCase] is a suspend function as opposed to the
 * [UseCase.execute] method of [UseCase].
 */

interface SuspendUseCase<in P,R>{

    /** Executes the use case asynchronously and returns a [Result].
     *
     * @return a [Result].
     *
     * @param parameters the input parameters to run the use case with
     */
    var coroutineDispatcher: CoroutineDispatcher
    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            // Moving all use case's executions to the injected dispatcher
            // In production code, this is usually the Default dispatcher (background thread)
            // In tests, this becomes a TestCoroutineDispatcher
            return withContext(coroutineDispatcher) {
                execute(parameters).let {
                    print("Checkiing list data getting: ${it.toString()}")
                     Result.Success(it)
                }
            }
        } catch (e: Exception) {
            //Timber.d(e)
            Result.Error(e)
        }
    }
    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    suspend fun execute(parameters: P): R
}
/** Invoke extension method on [SuspendUseCase] when there is no input parameter. */
suspend operator fun <R> SuspendUseCase<Unit,R>.invoke() = invoke(Unit)