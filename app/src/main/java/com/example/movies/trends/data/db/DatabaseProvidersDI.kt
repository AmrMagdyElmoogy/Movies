package com.example.movies.trends.data.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseProvidersDI {
    @Singleton
    @Provides
    fun provideDAOInstance(db: MovieDB): MoviesServiceDAO = db.dao()

    @Singleton
    @Provides
    fun provideDBInstance(
        @ApplicationContext context: Context,
    ): MovieDB {
        return Room.databaseBuilder(
            context,
            MovieDB::class.java,
            "Movies",
        ).fallbackToDestructiveMigration()
            .build()
    }
}
