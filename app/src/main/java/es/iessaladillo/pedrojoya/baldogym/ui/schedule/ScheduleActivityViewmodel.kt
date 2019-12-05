package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay
import es.iessaladillo.pedrojoya.baldogym.data.entity.getCurrentWeekDay

class ScheduleActivityViewmodel(private val repository: Repository) : ViewModel() {

    private val _currentWeekDay: MutableLiveData<WeekDay> = MutableLiveData(getCurrentWeekDay())
    val currentWeekDay: LiveData<WeekDay>
        get() = _currentWeekDay

    private val _trainingSessions: MutableLiveData<List<TrainingSession>> =
        MutableLiveData(repository.queryTrainingSessions(currentWeekDay.value!!))
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