package com.fizz.compose.di

import android.content.Context
import androidx.room.Room
import com.fizz.compose.data.room.FizzDatabase
import com.fizz.compose.data.room.dao.AccountDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideFizzDatabase(@ApplicationContext appContext: Context): FizzDatabase {
        return Room.databaseBuilder(appContext, FizzDatabase::class.java, "data.db").build()
    }

    @Provides
    fun provideChannelDao(appDatabase: FizzDatabase): AccountDao {
        return appDatabase.accountDao()
    }
}