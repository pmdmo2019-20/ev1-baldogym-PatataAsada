package es.iessaladillo.pedrojoya.baldogym.ui.trainingsession

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.baldogym.data.Repository

@Suppress("UNCHECKED_CAST")
class TrainingSessionViewmodelFactory(private val repository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrainingSessionViewmodel::class.java)) {
            return TrainingSessionViewmodel(repository) as T
        }
        throw IllegalArgumentException("Must provide TrainingSessionActivity class")
    }
}