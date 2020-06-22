package si.damjanh.androidhilt.data.net

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import si.damjanh.androidhilt.data.model.GrowZone
import si.damjanh.androidhilt.data.model.Plant
import si.damjanh.androidhilt.data.net.api.PlantsApi
import javax.inject.Inject

class PlantsNetworkService @Inject constructor(retrofit: Retrofit) : IPlantsNetworkService {

    private val service = retrofit.create(PlantsApi::class.java)

    override suspend fun allPlants(): List<Plant> = withContext(Dispatchers.Default) {
        delay(1500) // Simulate delay
        val result = service.getAllPlants()
        result.shuffled()
    }

    override suspend fun plantsByGrowZone(growZone: GrowZone) = withContext(Dispatchers.Default) {
        delay(1500) // Simulate delay
        val result = service.getAllPlants()
        result.filter { it.growZoneNumber == growZone.number }.shuffled()
    }

    override suspend fun customPlantSortOrder(): List<String> = withContext(Dispatchers.Default) {
        val result = service.getCustomPlantSortOrder()
        result.map { plant -> plant.plantId }
    }
}