package si.damjanh.androidhilt.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "plants")
data class Plant(
    @PrimaryKey @ColumnInfo(name ="id") val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val watteringInterval: Int = 7,
    val imageUrl: String = ""
) {
    override fun toString() = name
}

inline class GrowZone(val number: Int)
val NoGrowZone = GrowZone(-1)