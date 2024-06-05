package com.example.diary.diaryfeature.domain.repository

import com.example.diary.diaryfeature.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface DiaryRepository {

    fun getNotes() : Flow<List<Note>>

    suspend fun getNotebyId(id : Int) : Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)
}