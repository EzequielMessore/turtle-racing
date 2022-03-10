package br.com.messore.tech.turtleracing.data.local.di

import android.content.Context
import androidx.room.Room
import br.com.messore.tech.turtleracing.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    internal fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "turtle-bd"
        ).build()
    }

    @Provides
    internal fun providesTokenDao(appDatabase: AppDatabase) = appDatabase.tokenDao()

    @Provides
    internal fun providesTurtleDao(appDatabase: AppDatabase) = appDatabase.turtleDao()
}
