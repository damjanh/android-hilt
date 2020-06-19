package si.damjanh.androidhilt.data.repo

import androidx.lifecycle.LiveData
import si.damjanh.androidhilt.data.db.WordDao
import si.damjanh.androidhilt.data.model.Word

class WordRepository (private val wordDao: WordDao) {
    val allWords: LiveData<List<Word>> = wordDao.getWords();

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}