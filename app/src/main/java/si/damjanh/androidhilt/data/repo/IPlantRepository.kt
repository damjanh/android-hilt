package si.damjanh.androidhilt.data.repo

import androidx.lifecycle.LiveData
import si.damjanh.androidhilt.data.model.GrowZone
import si.damjanh.androidhilt.data.model.Plant

interface IPlantRepository {
    val plants: LiveData<List<Plant>>
    fun getPlantsWithGrowZone(growZone: GrowZone): LiveData<List<Plant>>
    suspend fun tryUpdateRecentPlantsCache()
}