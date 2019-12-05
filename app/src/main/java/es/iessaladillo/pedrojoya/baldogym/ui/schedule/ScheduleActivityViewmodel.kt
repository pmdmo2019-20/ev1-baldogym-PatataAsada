package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay
import es.iessaladillo.pedrojoya.baldogym.data.entity.getCurrentWeekDay

class ScheduleActivityViewmodel(private val repository: Repository) : ViewModel() {

    //Dia de la semana seleccionado
    private val _currentWeekDay: MutableLiveData<WeekDay> = MutableLiveData(getCurrentWeekDay())
    val currentWeekDay: LiveData<WeekDay>
        get() = _currentWeekDay

    //Lista de sesiones
    private val _trainingSessions: MutableLiveData<List<TrainingSession>> =
        MutableLiveData(repository.queryTrainingSessions(currentWeekDay.value!!))
    val trainingSessions: LiveData<List<TrainingSession>>
        get() = _trainingSessions

    private val _trainingSession: MutableLiveData<TrainingSession> =
        MutableLiveData()
    val trainingSession: LiveData<TrainingSession>
        get() = _trainingSession

    private val _selectedId: MutableLiveData<Long> =
        MutableLiveData(0)
    val selectedSession: LiveData<Long>
        get() = _selectedId

    //Cambia el dia por el introducido y recarga la lista de sesiones
    fun changeDay(weekDay: WeekDay) {
        _currentWeekDay.value = weekDay
        queryTrainingSessions()
    }

    //Carga la lista de sesiones según el dia en currentWeekDay
    fun queryTrainingSessions() {
        _trainingSessions.value = repository.queryTrainingSessions(currentWeekDay.value!!)
    }

    //Cambia el boolean de la sesion y actualiza la lista
    fun changeJoinState(trainingSession: TrainingSession) {
        repository.changeJoinState(trainingSession)
        queryTrainingSessions()
        queryTrainingSession()
    }

    fun queryTrainingSession() {
        _trainingSession.value = repository.queryTrainingSession(selectedSession.value!!)
    }

    fun setSelectedId(id:Long){
        _selectedId.value = id
    }
}