package si.damjanh.androidhilt.data.repo

import androidx.lifecycle.LiveData
import si.damjanh.androidhilt.data.model.Word

interface IWordRepository {
    val allWords: LiveData<List<Word>>
    suspend fun insert(word: Word)
}