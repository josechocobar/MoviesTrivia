package com.josechocobar.moviestrivia.utils

import androidx.room.TypeConverter

class DataTypeConverter {
    @TypeConverter
    fun listToString(value:List<Int>):String{
        var genre = ""
        value.forEach {
            genre += "$it,"
        }
        return genre
    }
    @TypeConverter
    fun stringToList(str : String):List<Int>{
        val mutableList = mutableListOf<Int>()
        val splitStr = str.split(",")
        splitStr.forEach {
            mutableList.add(it.toInt())
        }
        return mutableList
    }
}