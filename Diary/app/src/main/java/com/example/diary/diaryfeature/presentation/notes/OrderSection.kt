package com.example.diary.diaryfeature.presentation.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.diary.diaryfeature.domain.util.DiaryOrder
import com.example.diary.diaryfeature.domain.util.OrderType
import com.example.diary.diaryfeature.presentation.notes.components.DiaryEvent
import androidx.compose.ui.unit.dp


@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    diaryOrder: DiaryOrder = DiaryOrder.Date(OrderType.Descending),
    onOrderChange : (DiaryOrder) -> Unit
){
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(text = "Title", selected = diaryOrder is DiaryOrder.Title, onSelect = {
                onOrderChange(DiaryOrder.Title(diaryOrder.orderType))
            })
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(text = "Date", selected = diaryOrder is DiaryOrder.Title, onSelect = {
                onOrderChange(DiaryOrder.Date(diaryOrder.orderType))
            })
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(text = "Color", selected = diaryOrder is DiaryOrder.Title, onSelect = {
                onOrderChange(DiaryOrder.Color(diaryOrder.orderType))
            })
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(text = "Ascending", selected = diaryOrder.orderType is OrderType.Ascending, onSelect = {
                onOrderChange(diaryOrder.copy(OrderType.Ascending))
            })
            
            Spacer(modifier = Modifier.height(8.dp))

            DefaultRadioButton(text = "Descending", selected = diaryOrder.orderType is OrderType.Descending, onSelect = {
                onOrderChange(diaryOrder.copy(OrderType.Descending))
            })
        }
    }
}