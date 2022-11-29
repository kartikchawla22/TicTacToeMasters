package net.kartikchawla.tic_tac_toe_masters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class PastGameFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_past_game,container, false)
        val textView = view.findViewById<TextView>(R.id.pastGameId)
        val gameId = PastGameFragmentArgs.fromBundle(requireArguments()).gameId
        textView.text = gameId.toString()

        return view
    }


}