package com.example.diary.diaryfeature.presentation.notes.components

import com.example.diary.diaryfeature.domain.model.Note
import com.example.diary.diaryfeature.domain.util.DiaryOrder
import com.example.diary.diaryfeature.domain.util.OrderType

data class DiaryState(val notes : List<Note> = emptyList(),
    val diaryOrder: DiaryOrder = DiaryOrder.Date(OrderType.Descending),
    val isOrderSectionVisible : Boolean = false)