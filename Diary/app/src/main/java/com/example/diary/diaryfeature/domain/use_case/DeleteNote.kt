package com.example.diary.diaryfeature.domain.use_case

import com.example.diary.diaryfeature.domain.model.Note
import com.example.diary.diaryfeature.domain.repository.DiaryRepository

class DeleteNote(var repository: DiaryRepository) {

    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }
}