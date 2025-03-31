package com.r4dixx.cats.network.model

import kotlinx.serialization.Serializable

@Serializable
data class APIResult(val banks: List<APIBank>)
