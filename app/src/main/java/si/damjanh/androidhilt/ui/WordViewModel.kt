package si.damjanh.androidhilt.ui

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import si.damjanh.androidhilt.data.model.Word
import si.damjanh.androidhilt.data.repo.IWordRepository

class WordViewModel @ViewModelInject constructor(
    application: Application,
    private val repository: IWordRepository
) :
    AndroidViewModel(application) {
    val allWords: LiveData<List<Word>> = repository.allWords

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }
}