package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.baldogym.data.Repository

@Suppress("UNCHECKED_CAST")
class ScheduleActivityViewmodelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScheduleActivityViewmodel::class.java)) {
            return ScheduleActivityViewmodel(repository) as T
        }
        throw IllegalArgumentException("Must provide ScheduleActivityViewModel class")
    }
}