package es.iessaladillo.pedrojoya.baldogym.ui.trainingsession

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository
import kotlinx.android.synthetic.main.training_session_activity.*

class TrainingSessionActivity : AppCompatActivity() {

    @Suppress("PrivatePropertyName")
    private val TRAINING_SESSION = "TRAINING_SESSION"
    private val viewmodel: TrainingSessionViewmodel by viewModels {
        TrainingSessionViewmodelFactory(LocalRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.training_session_activity)
        catchTrainingSession(savedInstanceState)
        setupObservers()
        setupButtons()
    }

    //Configura el boton para unirse/salir de la sesion.
    private fun setupButtons() {
        btnSession.setOnClickListener { viewmodel.changeJoinState(viewmodel.trainingSession.value!!) }
    }

    //Observadores para cambiar los datos segun la sesion seleccionada y si está o no el usuario unido a ella
    private fun setupObservers() {
        viewmodel.trainingSession.observe(this) {
            lblDay.text = it.weekDay.name
            lblDescription.text = it.description
            lblParticipants.text =
                getString(R.string.session_participants, it.participants.toString())
            lblRoom.text = it.room
            lblSport.text = it.name
            lblTime.text = it.time
            lblTrainer.text = it.trainer
            imgSport.setBackgroundResource(it.photoResId)

            if (it.userJoined) {
                btnSession.setBackgroundResource(R.drawable.session_btn_quit_background)
                btnSession.setTextColor(Color.BLACK)
                btnSession.setText(R.string.schedule_item_quit)
            } else {
                btnSession.setBackgroundResource(R.drawable.session_btn_join_background)
                btnSession.setTextColor(Color.WHITE)
                btnSession.setText(R.string.schedule_item_join)
            }
        }
        viewmodel.selectedSession.observe(this) {
            viewmodel.queryTrainingSession()
        }
    }

    //Captura el intent y configura el viewmodel.
    private fun catchTrainingSession(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            if (intent == null || !intent.hasExtra(TRAINING_SESSION)) {
                throw RuntimeException("No llega el intent")
            }
            viewmodel.setSelectedId(intent.getLongExtra(TRAINING_SESSION, 0))
        }
    }

}
