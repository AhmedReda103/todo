package com.example.todo

import java.util.*


fun Calendar.clearTime(): Calendar {
    this.clear(Calendar.MILLISECOND)
    this.clear(Calendar.SECOND)
    this.clear(Calendar.MINUTE)
    this.clear(Calendar.HOUR)
    return this
}