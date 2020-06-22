package si.damjanh.androidhilt.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import si.damjanh.androidhilt.data.db.WordDao
import si.damjanh.androidhilt.data.db.AppDatabase
import si.damjanh.androidhilt.data.db.PlantDao
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "word_database"
        ).build()
    }

    @Provides
    fun provideWordDao(database: AppDatabase): WordDao {
        return database.wordDao()
    }

    @Provides
    fun providePlantDao(database: AppDatabase): PlantDao {
        return database.plantDao()
    }
}