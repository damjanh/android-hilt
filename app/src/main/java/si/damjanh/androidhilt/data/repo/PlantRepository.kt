package si.damjanh.androidhilt.data.repo

import androidx.lifecycle.LiveData
import si.damjanh.androidhilt.data.db.PlantDao
import si.damjanh.androidhilt.data.model.GrowZone
import si.damjanh.androidhilt.data.model.Plant
import si.damjanh.androidhilt.data.net.IPlantsNetworkService
import si.damjanh.androidhilt.util.CacheOnSuccess
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import si.damjanh.androidhilt.data.model.NoGrowZone
import si.damjanh.androidhilt.util.ComparablePair
import javax.inject.Inject

class PlantRepository @Inject constructor(
    private val plantDao: PlantDao,
    val plantsService: IPlantsNetworkService
) : IPlantRepository {

    private var plantsListSortOrderCache = CacheOnSuccess(onErrorFallback = { listOf<String>() }) {
        plantsService.customPlantSortOrder()
    }

    override val plants: LiveData<List<Plant>> = liveData {
        val plants = plantDao.getPlants()
        val customSortOrder = plantsListSortOrderCache.getOrAwait()
        emitSource(plants.map { plantList -> plantList.applySort(customSortOrder) })
    }

    override fun getPlantsWithGrowZone(growZone: GrowZone) =
        plantDao.getPlantsWithGrowZoneNumber(growZone.number)

    private fun List<Plant>.applySort(customSortOrder: List<String>): List<Plant> {
        return sortedBy { plant ->
            val positionForItem = customSortOrder.indexOf(plant.plantId).let { order ->
                if (order > -1) order else Int.MAX_VALUE
            }
            ComparablePair(positionForItem, plant.name)
        }
    }

    override suspend fun tryUpdateRecentPlantsCache() {
        if (shouldUpdatePlantsCache(NoGrowZone)) fetchRecentPlants()
    }

    private suspend fun shouldUpdatePlantsCache(growZone: GrowZone): Boolean {
        // TODO: Check db here
        return true
    }

    private suspend fun fetchRecentPlants() {
        val plants = plantsService.allPlants()
        plantDao.insertAll(plants)
    }
}