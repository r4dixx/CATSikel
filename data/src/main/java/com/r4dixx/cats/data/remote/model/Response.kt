package com.r4dixx.cats.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Response(val banks: List<Bank>)
