package es.iessaladillo.pedrojoya.baldogym.ui.trainingsession

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession

class TrainingSessionViewmodel(private val repository: Repository) : ViewModel() {

    //Sesion seleccionada
    private val _trainingSession: MutableLiveData<TrainingSession> =
        MutableLiveData()
    val trainingSession: LiveData<TrainingSession>
        get() = _trainingSession

    //id de la sesion seleccionada
    private val _selectedId: MutableLiveData<Long> =
        MutableLiveData(0)
    val selectedSession: LiveData<Long>
        get() = _selectedId

    //Cambia el boolean de la sesion y actualiza la lista
    fun changeJoinState(trainingSession: TrainingSession) {
        repository.changeJoinState(trainingSession)
        queryTrainingSession()
    }

    //Pide la sesion al repositorio
    fun queryTrainingSession() {
        _trainingSession.value = repository.queryTrainingSession(selectedSession.value!!)
    }

    //Cambia la id de la sesion seleccionada
    fun setSelectedId(id: Long) {
        _selectedId.value = id
    }

}