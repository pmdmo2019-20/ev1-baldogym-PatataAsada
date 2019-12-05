package es.iessaladillo.pedrojoya.baldogym.data

import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay

interface Repository {

    //Llamma las sesiones que se realizan ese dia.
    fun queryTrainingSessions(weekDay: WeekDay): List<TrainingSession>

    //Llama a una de las sesiones.
    fun queryTrainingSession(id: Long): TrainingSession?

    //Cambia el estado de userJoined para unir/salir de los participantes.
    fun changeJoinState(trainingSession: TrainingSession)

}