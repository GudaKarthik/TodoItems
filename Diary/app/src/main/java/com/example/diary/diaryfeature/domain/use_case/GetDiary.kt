package com.example.diary.diaryfeature.domain.use_case

import androidx.compose.ui.text.toLowerCase
import com.example.diary.diaryfeature.data.datasource.DiaryDao
import com.example.diary.diaryfeature.domain.model.Note
import com.example.diary.diaryfeature.domain.repository.DiaryRepository
import com.example.diary.diaryfeature.domain.util.DiaryOrder
import com.example.diary.diaryfeature.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetDiary(private val diaryRepository: DiaryRepository) {

    operator fun invoke(diaryOrder: DiaryOrder = DiaryOrder.Date(OrderType.Descending)) : Flow<List<Note>>{
        return diaryRepository.getNotes().map { diary ->
            when(diaryOrder.orderType){
                is OrderType.Ascending ->{
                    when(diaryOrder){
                        is DiaryOrder.Title -> diary.sortedBy { it.title.toLowerCase() }
                        is DiaryOrder.Date -> diary.sortedBy { it.timestamp }
                        is DiaryOrder.Color -> diary.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when(diaryOrder){
                        is DiaryOrder.Title -> diary.sortedByDescending { it.title.toLowerCase() }
                        is DiaryOrder.Date -> diary.sortedByDescending { it.timestamp }
                        is DiaryOrder.Color -> diary.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}