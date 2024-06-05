package com.example.diary.diaryfeature.data.repository

import com.example.diary.diaryfeature.data.datasource.DiaryDao
import com.example.diary.diaryfeature.domain.model.Note
import com.example.diary.diaryfeature.domain.repository.DiaryRepository
import kotlinx.coroutines.flow.Flow

class DiaryRepositoryImpl(var diaryDao: DiaryDao) : DiaryRepository {
    override fun getNotes(): Flow<List<Note>> {
        return diaryDao.getNotes()
    }

    override suspend fun getNotebyId(id : Int): Note? {
        return diaryDao.getNotebyId(id)
    }

    override suspend fun insertNote(note: Note) {
        return diaryDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return diaryDao.deleteNote(note)
    }
}