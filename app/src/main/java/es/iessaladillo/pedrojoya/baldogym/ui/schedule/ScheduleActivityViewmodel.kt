package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay

class ScheduleActivityViewmodel(private val repository: Repository) : ViewModel() {

    //Dia de la semana seleccionado
    private val _currentWeekDay: MutableLiveData<WeekDay> = MutableLiveData()
    val currentWeekDay: LiveData<WeekDay>
        get() = _currentWeekDay

    //Lista de sesiones
    private val _trainingSessions: MutableLiveData<List<TrainingSession>> =
        MutableLiveData()
    val trainingSessions: LiveData<List<TrainingSession>>
        get() = _trainingSessions


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
    }
}