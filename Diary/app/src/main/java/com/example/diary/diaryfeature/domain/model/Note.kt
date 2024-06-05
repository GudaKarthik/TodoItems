package com.example.diary.diaryfeature.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diary.ui.theme.BabyBlue
import com.example.diary.ui.theme.LightGreen
import com.example.diary.ui.theme.RedOrange
import com.example.diary.ui.theme.RedPink
import com.example.diary.ui.theme.Violet

@Entity
data class Note(val title : String,
    var content : String,
    var timestamp : Long,
    var color : Int,
    @PrimaryKey val id : Int? = null) {

    companion object{
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message : String) : Exception(message)