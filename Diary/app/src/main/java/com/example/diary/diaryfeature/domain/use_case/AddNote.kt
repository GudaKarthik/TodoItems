package com.example.diary.diaryfeature.domain.use_case

import com.example.diary.diaryfeature.domain.model.InvalidNoteException
import com.example.diary.diaryfeature.domain.model.Note
import com.example.diary.diaryfeature.domain.repository.DiaryRepository

class AddNote(private var repository: DiaryRepository) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if (note.title.isBlank()){
            InvalidNoteException("The title of the note cant be empty")
        }
        if (note.content.isBlank()){
            InvalidNoteException("The content cannot be empty")
        }
        repository.insertNote(note)
    }
}