package net.kartikchawla.tic_tac_toe_masters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import net.kartikchawla.tic_tac_toe_masters.database.Games
import net.kartikchawla.tic_tac_toe_masters.databinding.ScoreItemBinding

class ScoreItemAdapter : ListAdapter<Games, ScoreItemAdapter.ScoreItemViewHolder>(ScoreDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreItemViewHolder = ScoreItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: ScoreItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ScoreItemViewHolder(val binding : ScoreItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object{
            fun inflateFrom(parent: ViewGroup) : ScoreItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
//                val view = layoutInflater.inflate(R.layout.score_item,parent,false) as TextView

                val binding = ScoreItemBinding.inflate(layoutInflater, parent, false)
                return ScoreItemViewHolder(binding)
            }
        }

        fun bind(item: Games) {
            binding.score = item
        }
    }
}