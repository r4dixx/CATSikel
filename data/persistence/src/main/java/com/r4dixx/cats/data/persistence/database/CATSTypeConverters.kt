package com.r4dixx.cats.data.persistence.database

import androidx.room.TypeConverter
import java.math.BigDecimal
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
class CATSTypeConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Instant? {
        return value?.let { Instant.fromEpochMilliseconds(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Instant?): Long? {
        return date?.toEpochMilliseconds()
    }

    @TypeConverter
    fun fromBigDecimalString(value: String?): BigDecimal? {
        return value?.let { BigDecimal(it) }
    }

    @TypeConverter
    fun bigDecimalToString(value: BigDecimal?): String? {
        return value?.toPlainString()
    }
}