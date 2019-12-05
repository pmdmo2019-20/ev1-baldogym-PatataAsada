package es.iessaladillo.pedrojoya.baldogym.ui.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.baldogym.R
import es.iessaladillo.pedrojoya.baldogym.data.entity.TrainingSession
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.schedule_activity.lblDay
import kotlinx.android.synthetic.main.schedule_activity_item.lblParticipants
import kotlinx.android.synthetic.main.schedule_activity_item.lblRoom
import kotlinx.android.synthetic.main.schedule_activity_item.lblSport
import kotlinx.android.synthetic.main.training_session_activity.*

class ScheduleActivityAdapter : RecyclerView.Adapter<ScheduleActivityAdapter.ViewHolder>() {

    private var onClickListener: OnItemClickListener? = null
    private var onJoinClickListener: OnJoinClickListener? = null
    private var data: List<TrainingSession> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemView = layoutInflater.inflate(R.layout.schedule_activity_item, parent, false)

        return ViewHolder(itemView, onClickListener, onJoinClickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setOnJoinClickListener(listener: OnJoinClickListener) {
        onJoinClickListener = listener
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onClickListener = listener
    }

    fun submitList(taskList: List<TrainingSession>) {
        data = taskList
        notifyDataSetChanged()
    }

    fun getItem(adapterPosition: Int): TrainingSession {
        return data[adapterPosition]
    }

    inner class ViewHolder(
        override val containerView: View,
        onClickListener: OnItemClickListener?,
        onJoinClickListener: OnJoinClickListener?
    ) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        init {
            containerView.setOnClickListener { onClickListener?.ontItemClick(adapterPosition) }
            btnSession.setOnClickListener { onJoinClickListener?.onJoinClick(adapterPosition) }
        }

        fun bind(trainingSession: TrainingSession) {
            lblDay.text = trainingSession.weekDay.name
            lblRoom.text = trainingSession.room
            lblParticipants.text = trainingSession.participants.toString()
            lblSport.text = trainingSession.name
            lblDescription.text = trainingSession.description
            imgSport.setImageResource(trainingSession.photoResId)
            lblTrainer.text = trainingSession.trainer
            lblTime.text = trainingSession.time
        }
    }

    interface OnItemClickListener {
        fun ontItemClick(adapterPosition: Int)
    }

    interface OnJoinClickListener {
        fun onJoinClick(adapterPosition: Int)
    }
}