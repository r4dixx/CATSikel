package com.r4dixx.cats.data.local.database

import androidx.room.TypeConverter
import java.math.BigDecimal
import kotlin.time.Duration.Companion.seconds
import kotlin.time.Instant

class CATSTypeConverters {

    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal? {
        return value?.let { BigDecimal(it) }
    }

    @TypeConverter
    fun fromInstant(value: Instant?): Long? {
        return value?.toEpochMilliseconds()?.seconds?.inWholeSeconds
    }

    @TypeConverter
    fun toInstant(value: Long?): Instant? {
        return value?.let { Instant.fromEpochSeconds(it) }
    }
}
