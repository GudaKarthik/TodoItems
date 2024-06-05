package com.example.diary.diaryfeature.domain.util

sealed class DiaryOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : DiaryOrder(orderType)
    class Date(orderType: OrderType) : DiaryOrder(orderType)
    class Color(orderType: OrderType) : DiaryOrder(orderType)

    fun copy(orderType: OrderType) : DiaryOrder{
        return when(this){
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }

}