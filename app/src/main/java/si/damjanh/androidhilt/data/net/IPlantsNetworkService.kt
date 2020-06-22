package si.damjanh.androidhilt.data.net

import si.damjanh.androidhilt.data.model.GrowZone
import si.damjanh.androidhilt.data.model.Plant

interface IPlantsNetworkService {
    suspend fun allPlants(): List<Plant>
    suspend fun plantsByGrowZone(growZone: GrowZone): List<Plant>
    suspend fun customPlantSortOrder(): List<String>
}