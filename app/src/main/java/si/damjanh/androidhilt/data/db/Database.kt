package si.damjanh.androidhilt.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import si.damjanh.androidhilt.data.model.Plant
import si.damjanh.androidhilt.data.model.Word

@Database(entities = [Word::class, Plant::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun plantDao(): PlantDao
}