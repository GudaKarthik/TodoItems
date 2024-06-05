package com.example.diary.diaryfeature.presentation.util

sealed class Screen(var route : String) {
    object NotesScreen : Screen("notes_screen")
    object AddEditNoteScreen : Screen("add_edit_note_screen")
}