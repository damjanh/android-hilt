package si.damjanh.androidhilt.ui.plants

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import si.damjanh.androidhilt.data.model.Plant
import si.damjanh.androidhilt.data.repo.IPlantRepository

class PlantsListViewModel @ViewModelInject constructor(plantRepository: IPlantRepository) :
    ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _snackbar = MutableLiveData<String?>()
    val snackbar: LiveData<String?>
        get() = _snackbar

    val plants: LiveData<List<Plant>> = plantRepository.plants

    fun onSnackbarShown() {
        _snackbar.value = null
    }

    init {
        launchDataLoad { plantRepository.tryUpdateRecentPlantsCache() }
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _loading.value = true
                block()
            } catch (error: Throwable) {
                _snackbar.value = error.message
            } finally {
                _loading.value = false
            }
        }
    }
}