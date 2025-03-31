package com.r4dixx.cats.data.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Response(val banks: List<Bank>)
