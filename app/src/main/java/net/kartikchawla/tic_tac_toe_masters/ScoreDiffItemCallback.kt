package net.kartikchawla.tic_tac_toe_masters

import androidx.recyclerview.widget.DiffUtil
import net.kartikchawla.tic_tac_toe_masters.database.Games

class ScoreDiffItemCallback : DiffUtil.ItemCallback<Games>() {

    override fun areItemsTheSame(oldItem: Games, newItem: Games) = (oldItem.gameID == newItem.gameID)

    override fun areContentsTheSame(oldItem: Games, newItem: Games) = (oldItem == newItem)
}