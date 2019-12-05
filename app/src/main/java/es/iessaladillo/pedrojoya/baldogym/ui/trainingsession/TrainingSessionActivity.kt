package es.iessaladillo.pedrojoya.baldogym.ui.trainingsession

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository
import es.iessaladillo.pedrojoya.baldogym.data.Repository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import kotlinx.android.synthetic.main.training_session_activity.*

class TrainingSessionActivity : AppCompatActivity() {

    val TRAINING_SESSION = "TRAINING_SESSION"
    private val trainingSession: TrainingSession? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.training_session_activity)
        catchTrainingSession(savedInstanceState)
        setupViews()
    }

    private fun catchTrainingSession(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            if (intent == null || !intent.hasExtra(TRAINING_SESSION)) {
                throw RuntimeException("No llega el intent")
            }

        }
    }

    private fun setupViews() {
        lblDay.text
    }
}
