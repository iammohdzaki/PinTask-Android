package com.zaki.pintask.utils

import com.zaki.pintask.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

/**
 * Created by Zaki on 13-10-2023.
 */
object DateUtils {

    fun formatTo(value: Long, format: String = DateFormats.NORMAL_DATE_FORMAT): String {
        val dateValue = Date(value)
        return SimpleDateFormat(format, Locale.ENGLISH).format(dateValue)
    }

    fun formatTime(inputTime: Long): UiText {
        val currentTime = System.currentTimeMillis()
        return when (TimeUnit.DAYS.convert(inputTime - currentTime, TimeUnit.MILLISECONDS)) {
            0L -> UiText.StringResource(R.string.today)
            -1L -> UiText.StringResource(R.string.yesterday)
            1L -> UiText.StringResource(R.string.tomorrow)
            else -> UiText.DynamicString(
                SimpleDateFormat(
                    "dd MMM yyyy",
                    Locale.ENGLISH
                ).format(Date(inputTime))
            )
        }
    }

    object DateFormats {
        const val NORMAL_DATE_FORMAT = "dd MMM yyyy"
    }

}