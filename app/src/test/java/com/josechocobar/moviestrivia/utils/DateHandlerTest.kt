package com.josechocobar.moviestrivia.utils

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class DateHandlerTest{
    lateinit var dateHandler: DateHandler
    lateinit var date : LocalDateTime
    lateinit var dateSmaller : LocalDateTime
    lateinit var dateTaller : LocalDateTime
    lateinit var dateBiggestThanThirdty : LocalDateTime


    @Before
    fun setup(){
        dateHandler = DateHandler()
        date= LocalDateTime.now()
        dateSmaller=date.minusMinutes(5)
        dateTaller = date.plusMinutes(5)
        dateBiggestThanThirdty = date.plusMinutes(35)
    }
    @Test
    fun withSmallerDateGetDifference(){
        Assert.assertEquals(-5,dateHandler.getDifference(date,dateSmaller))
    }
    @Test
    fun with_same_date_get_difference(){
        assertEquals(0,dateHandler.getDifference(date,date))
    }
    @Test
    fun with_taller_date_get_difference(){
        assertEquals(5,dateHandler.getDifference(date,dateTaller))
    }
    @Test
    fun with_biggest_date_get_difference(){
        assertEquals(35,dateHandler.getDifference(date,dateBiggestThanThirdty))
    }

    @Test
    fun with_smallest_date_is_less_t(){
        assertEquals(true,dateHandler.isLessThanT(date,dateSmaller))
    }
    @Test
    fun with_equal_date_date_is_less_t(){
        assertEquals(true,dateHandler.isLessThanT(date,date))
    }
    @Test
    fun with_taller_date_is_less_t(){
        assertEquals(true,dateHandler.isLessThanT(date,dateTaller))
    }
    @Test
    fun with_biggest_date_is_less_t(){
        assertEquals(false,dateHandler.isLessThanT(date,dateBiggestThanThirdty))
    }
}