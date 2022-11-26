package net.kartikchawla.tic_tac_toe_masters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.kartikchawla.tic_tac_toe_masters.database.Games

class ScoreItemAdapter : RecyclerView.Adapter<ScoreItemAdapter.ScoreItemViewHolder>() {
    var data = listOf<Games>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreItemViewHolder = ScoreItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: ScoreItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class ScoreItemViewHolder(val rootView : TextView) : RecyclerView.ViewHolder(rootView) {
        companion object{
            fun inflateFrom(parent: ViewGroup) : ScoreItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.score_item,parent,false) as TextView

                return ScoreItemViewHolder(view)
            }
        }

        fun bind(item: Games) {
            rootView.text = item.whoWon + " Won!!"
        }
    }
}