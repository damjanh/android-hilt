package si.damjanh.androidhilt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import si.damjanh.androidhilt.data.db.PlantDao
import si.damjanh.androidhilt.data.db.WordDao
import si.damjanh.androidhilt.data.net.IPlantsNetworkService
import si.damjanh.androidhilt.data.repo.IPlantRepository
import si.damjanh.androidhilt.data.repo.IWordRepository
import si.damjanh.androidhilt.data.repo.PlantRepository
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

    @Provides
    @Singleton
    fun providePlantRepository(
        plantDao: PlantDao,
        plantsService: IPlantsNetworkService
    ): IPlantRepository = PlantRepository(plantDao, plantsService)
}