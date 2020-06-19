package si.damjanh.androidhilt.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import si.damjanh.androidhilt.data.model.Word

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}