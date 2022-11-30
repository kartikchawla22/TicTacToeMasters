package net.kartikchawla.tic_tac_toe_masters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import net.kartikchawla.tic_tac_toe_masters.database.GameDataBase
import net.kartikchawla.tic_tac_toe_masters.databinding.FragmentPastGameBinding
import net.kartikchawla.tic_tac_toe_masters.viewModelFactories.PastGameViewModelFactory
import net.kartikchawla.tic_tac_toe_masters.viewModels.PastGameViewModel

class PastGameFragment : Fragment() {

    private var _binding: FragmentPastGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentPastGameBinding.inflate(inflater, container, false)
        val view = binding.root

        val gameId = PastGameFragmentArgs.fromBundle(requireArguments()).gameId

        val application = requireNotNull(this.activity).application
        val dao = GameDataBase.getInstance(application).gameDao

        val viewModelFactory = PastGameViewModelFactory(gameId, dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(PastGameViewModel::class.java)

        binding.pastGameViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.game.observe(viewLifecycleOwner, Observer { pastGame ->
            if (pastGame.gameMoves != ""){
                viewModel.setMovements(pastGame.gameMoves)
            }
        })


        binding.homeButton.setOnClickListener() {
            val action = PastGameFragmentDirections.actionPastGameFragmentToHomeFragment()
            view.findNavController().navigate(directions = action)
        }

        return view
    }


}