package com.lubnamariyam.soho.di

import android.content.Context
import androidx.room.Room
import com.lubnamariyam.soho.database.RandomUserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ):RandomUserDatabase{
        return Room.databaseBuilder(
            context, RandomUserDatabase::class.java,
            "random_user_database"
        ).build()
    }

}