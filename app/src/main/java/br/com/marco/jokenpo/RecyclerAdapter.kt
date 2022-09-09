package br.com.marco.jokenpo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.ArrayList
import androidx.recyclerview.widget.RecyclerView
import br.com.marco.jokenpo.firestore.Ranking

class CustomAdapter(private val context: Context, private val list: ArrayList<Ranking>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvLabel: TextView = itemView.findViewById(R.id.tv_label)



        fun bind(position: Int) {
            // This method will be called anytime a list item is created or update its data
            //Do your stuff here
            tvLabel.text = list[position].nome
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_ranking, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}