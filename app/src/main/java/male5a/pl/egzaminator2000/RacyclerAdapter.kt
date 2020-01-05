package male5a.pl.egzaminator2000


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardexamview.view.*


class RecyclerAdapter(private val items : ArrayList<ExamData>, val clickListener: (ExamData, Int) -> Unit) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(view = LayoutInflater.from(parent.context).inflate(R.layout.cardexamview, parent, false))
    }
    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var item : ExamData = items[position]

        // Calling the clickListener sent by the constructor
        holder?.itemView?.setOnClickListener { clickListener(item, position) }

        holder?.lblName?.text = items[position].name
        holder?.lblDate?.text = items[position].date
        holder!!.lblTime.text = items[position].time
    }


    public fun removeItem(ViewHolder:RecyclerView.ViewHolder){
        items.removeAt(ViewHolder.adapterPosition)
        notifyItemRemoved(ViewHolder.adapterPosition)
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val lblName = view.lblName
    val lblDate = view.lblDate
    val lblTime = view.lblTime
}