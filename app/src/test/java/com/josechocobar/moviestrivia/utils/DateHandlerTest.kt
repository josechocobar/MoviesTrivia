package com.josechocobar.moviestrivia.utils

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime

class DateHandlerTest {
    val dateOne = LocalDateTime.now()
    val dateTwo = dateOne.plusMinutes(5)
    val dateThree = dateOne.plusMinutes(45)
    var dateHandler:DateHandler? = null
    @Before
    fun setUp(){
        dateHandler=DateHandler()
    }

    @Test
    fun getDifference() {
        val expected :Long = 5
        Assert.assertEquals(expected, dateHandler?.getDifference(dateOne, dateTwo))
    }

    @Test
    fun isLessThanTestYes() {
        Assert.assertEquals(true,dateHandler?.isLessThanT(dateOne,dateTwo))
    }
    @Test
    fun isLessThanTestNo(){
        Assert.assertEquals(false,dateHandler?.isLessThanT(dateOne,dateThree))
    }
}