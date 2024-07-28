package dev.yuanzix.revitalize.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.yuanzix.revitalize.data.api.API
import dev.yuanzix.revitalize.data.database.ReVitalizeDatabase
import dev.yuanzix.revitalize.data.repository.DataStoreRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAPI(): API = API

    @Provides
    @Singleton
    fun provideDataStoreRepository(@ApplicationContext context: Context): DataStoreRepository =
        DataStoreRepository(context)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        ReVitalizeDatabase::class.java,
        "revitalize_database"
    ).build()

    @Provides
    @Singleton
    fun provideAttendanceDao(database: ReVitalizeDatabase) = database.getAttendanceDao()

    @Provides
    @Singleton
    fun provideProfileDao(database: ReVitalizeDatabase) = database.getProfileDao()

    @Provides
    @Singleton
    fun provideGradeHistoryDao(database: ReVitalizeDatabase) = database.getGradeHistoryDao()

    @Provides
    @Singleton
    fun provideSemesterDetailsDao(database: ReVitalizeDatabase) = database.getSemesterDetailsDao()

    @Provides
    @Singleton
    fun provideTimeTableDao(database: ReVitalizeDatabase) = database.getTimeTableDao()
}