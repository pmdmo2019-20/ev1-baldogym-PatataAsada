package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.LocalRepository
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import es.iessaladillo.pedrojoya.baldogym.data.entity.WeekDay
import es.iessaladillo.pedrojoya.baldogym.data.entity.getCurrentWeekDay
import es.iessaladillo.pedrojoya.baldogym.ui.trainingsession.TrainingSessionActivity
import kotlinx.android.synthetic.main.schedule_activity.*

class ScheduleActivity : AppCompatActivity(), ScheduleActivityAdapter.OnItemClickListener,
    ScheduleActivityAdapter.OnJoinClickListener {

    val TRAINING_SESSION: String = "TRAINING_SESSION"

    private val viewmodel: ScheduleActivityViewmodel by viewModels {
        ScheduleActivityViewmodelFactory(LocalRepository)
    }

    private val listAdapter: ScheduleActivityAdapter = ScheduleActivityAdapter().also {
        it.setOnItemClickListener(this)
        it.setOnJoinClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity)
        setupObservers()
        setupViews()
    }

    private fun setupViews() {
        viewmodel.changeDay(getCurrentWeekDay())
        setupReciclerView()
        setupDayClickListeners()
        setBold(viewmodel.currentWeekDay.value!!)
        lblDay.text = viewmodel.currentWeekDay.value!!.name
    }

    private fun setupDayClickListeners() {
        lblMonday.setOnClickListener { changeDaySelected(WeekDay.MONDAY) }
        lblTuesday.setOnClickListener { changeDaySelected(WeekDay.TUESDAY) }
        lblWednesday.setOnClickListener { changeDaySelected(WeekDay.WEDNESDAY) }
        lblThursday.setOnClickListener { changeDaySelected(WeekDay.THURSDAY) }
        lblFriday.setOnClickListener { changeDaySelected(WeekDay.FRIDAY) }
        lblSaturday.setOnClickListener { changeDaySelected(WeekDay.SATURDAY) }
        lblSunday.setOnClickListener { changeDaySelected(WeekDay.SUNDAY) }
    }

    private fun changeDaySelected(weekDay: WeekDay) {
        setBold(weekDay)
        viewmodel.changeDay(weekDay)
    }

    private fun setupReciclerView() {
        lstTrainingSessions.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@ScheduleActivity)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(
                DividerItemDecoration(
                    this@ScheduleActivity,
                    RecyclerView.VERTICAL
                )
            )
            adapter = listAdapter
        }
    }

    //Observa los cambios en la lista de sesiones y del dia seleccionado
    private fun setupObservers() {
        viewmodel.trainingSessions.observe(this) {
            if (it.isNotEmpty()) lblEmptyView.visibility = View.INVISIBLE
            else lblEmptyView.visibility = View.VISIBLE
            listAdapter.submitList(viewmodel.trainingSessions.value!!)
        }
        viewmodel.currentWeekDay.observe(this) {
            lblDay.text = it.name
            setBold(it)
        }
    }

    //cambio de letra según el seleccionado
    private fun setBold(weekDay: WeekDay) {
        lblMonday.typeface = Typeface.DEFAULT
        lblTuesday.typeface = Typeface.DEFAULT
        lblWednesday.typeface = Typeface.DEFAULT
        lblThursday.typeface = Typeface.DEFAULT
        lblFriday.typeface = Typeface.DEFAULT
        lblSaturday.typeface = Typeface.DEFAULT
        lblSunday.typeface = Typeface.DEFAULT
        when (weekDay) {
            WeekDay.MONDAY -> lblMonday.typeface = Typeface.DEFAULT_BOLD
            WeekDay.TUESDAY -> lblTuesday.typeface = Typeface.DEFAULT_BOLD
            WeekDay.WEDNESDAY -> lblWednesday.typeface = Typeface.DEFAULT_BOLD
            WeekDay.THURSDAY -> lblThursday.typeface = Typeface.DEFAULT_BOLD
            WeekDay.FRIDAY -> lblFriday.typeface = Typeface.DEFAULT_BOLD
            WeekDay.SATURDAY -> lblSaturday.typeface = Typeface.DEFAULT_BOLD
            WeekDay.SUNDAY -> lblSunday.typeface = Typeface.DEFAULT_BOLD
        }
    }

    //Accion cuando hace click en el objeto TrainingSession
    override fun ontItemClick(adapterPosition: Int) {
        showSession(listAdapter.getItem(adapterPosition))
    }

    //Abre intent con la sesion presionada
    private fun showSession(item: TrainingSession) {
        val newintent = Intent(this, TrainingSessionActivity::class.java)
            .putExtra(TRAINING_SESSION, item.id)
        startActivity(newintent)
    }

    //Accion cuando presiona el boton "JOIN" o "QUIT"
    override fun onJoinClick(adapterPosition: Int) {
        viewmodel.changeJoinState(listAdapter.getItem(adapterPosition))
    }

}
