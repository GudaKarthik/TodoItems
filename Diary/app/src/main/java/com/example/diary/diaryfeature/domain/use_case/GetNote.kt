package com.example.diary.diaryfeature.domain.use_case

import com.example.diary.diaryfeature.domain.model.Note
import com.example.diary.diaryfeature.domain.repository.DiaryRepository

class GetNote(private val repository: DiaryRepository) {

    suspend operator fun invoke(id : Int) : Note?{
        return repository.getNotebyId(id)
    }
}