package com.josechocobar.moviestrivia.utils

import java.time.Duration
import java.time.LocalDateTime

class DateHandler {
    fun getDifference(dateOne : LocalDateTime, dateTwo : LocalDateTime): Long {
        return Duration.between(dateOne,dateTwo).toMinutes()
    }
    fun isLessThanT(dateOne: LocalDateTime,dateTwo: LocalDateTime): Boolean {
        return getDifference(dateOne,dateTwo) <30
    }

}