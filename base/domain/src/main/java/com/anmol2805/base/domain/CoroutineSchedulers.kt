package com.anmol2805.base.domain

import androidx.annotation.Keep
import kotlinx.coroutines.CoroutineDispatcher

@Keep
data class CoroutineSchedulers(
    val default: CoroutineDispatcher,
    val main: CoroutineDispatcher,
    val io: CoroutineDispatcher
)