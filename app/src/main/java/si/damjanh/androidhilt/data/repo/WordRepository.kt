package si.damjanh.androidhilt.data.repo

import androidx.lifecycle.LiveData
import si.damjanh.androidhilt.data.db.WordDao
import si.damjanh.androidhilt.data.model.Word
import javax.inject.Inject

class WordRepository @Inject constructor(private val wordDao: WordDao): IWordRepository {
    override val allWords: LiveData<List<Word>> = wordDao.getWords();

    override suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}