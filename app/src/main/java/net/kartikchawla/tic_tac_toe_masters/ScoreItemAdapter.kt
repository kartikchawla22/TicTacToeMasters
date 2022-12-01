package net.kartikchawla.tic_tac_toe_masters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import net.kartikchawla.tic_tac_toe_masters.database.Games
import net.kartikchawla.tic_tac_toe_masters.databinding.ScoreItemBinding

class ScoreItemAdapter(val clickListener: (gameID: Long) -> Unit) : ListAdapter<Games, ScoreItemAdapter.ScoreItemViewHolder>(ScoreDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreItemViewHolder = ScoreItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: ScoreItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ScoreItemViewHolder(val binding : ScoreItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object{
            fun inflateFrom(parent: ViewGroup) : ScoreItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ScoreItemBinding.inflate(layoutInflater, parent, false)
                return ScoreItemViewHolder(binding)
            }
        }

        fun bind(item: Games, onClickListener: (gameID: Long) -> Unit) {
            binding.score = item
            binding.root.setOnClickListener{
                onClickListener(item.gameID)
            }
        }
    }
}