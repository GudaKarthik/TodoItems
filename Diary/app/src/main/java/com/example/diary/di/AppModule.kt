package com.example.diary.di

import android.app.Application
import android.provider.ContactsContract.Data
import androidx.room.Room
import com.example.diary.diaryfeature.data.datasource.Database
import com.example.diary.diaryfeature.data.repository.DiaryRepositoryImpl
import com.example.diary.diaryfeature.domain.repository.DiaryRepository
import com.example.diary.diaryfeature.domain.use_case.AddNote
import com.example.diary.diaryfeature.domain.use_case.DeleteNote
import com.example.diary.diaryfeature.domain.use_case.DiaryUseCases
import com.example.diary.diaryfeature.domain.use_case.GetDiary
import com.example.diary.diaryfeature.domain.use_case.GetNote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDiaryDatabase(app : Application) : Database{
        return Room.databaseBuilder(app,
            Database::class.java,
            Database.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideDiaryRepository(db : Database) : DiaryRepository{
        return DiaryRepositoryImpl(db.diaryDao)
    }

    @Provides
    @Singleton
    fun provideDiaryUseCases(repository: DiaryRepository) : DiaryUseCases{
        return DiaryUseCases(getNotes = GetDiary(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }
}