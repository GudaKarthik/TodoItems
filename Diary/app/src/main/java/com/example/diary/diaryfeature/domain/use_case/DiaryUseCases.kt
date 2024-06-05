package com.example.diary.diaryfeature.domain.use_case

data class DiaryUseCases(var getNotes : GetDiary,
    var deleteNote: DeleteNote,
    var addNote: AddNote,
    var getNote : GetNote)