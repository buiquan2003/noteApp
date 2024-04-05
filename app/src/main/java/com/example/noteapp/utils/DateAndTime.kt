package com.example.noteapp.utils

import java.util.Calendar

object DateAndTime {

    fun getHour(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.HOUR)
    }

    fun getMinute(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.MINUTE)
    }

    fun getDay(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.DAY_OF_MONTH)

    }

    fun dayOfMonth(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.MONTH) + 1

    }

}