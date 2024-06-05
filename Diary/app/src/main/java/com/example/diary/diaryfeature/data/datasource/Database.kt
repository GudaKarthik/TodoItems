package com.example.diary.diaryfeature.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.diary.diaryfeature.domain.model.Note

@Database(entities = [Note::class],
    version = 1)
abstract class Database : RoomDatabase() {

    abstract val diaryDao : DiaryDao

    companion object{
        const val DATABASE_NAME = "diary_db"
    }
}