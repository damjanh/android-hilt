package si.damjanh.androidhilt.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import si.damjanh.androidhilt.data.db.WordDao
import si.damjanh.androidhilt.data.repo.IWordRepository
import si.damjanh.androidhilt.data.repo.WordRepository
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWordRepository(
        wordDao: WordDao
    ): IWordRepository = WordRepository(wordDao)
}