package net.kartikchawla.tic_tac_toe_masters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.findNavController
import net.kartikchawla.tic_tac_toe_masters.database.GameDataBase
import net.kartikchawla.tic_tac_toe_masters.databinding.FragmentGameBoardBinding
import net.kartikchawla.tic_tac_toe_masters.viewModelFactories.GameViewModelFactory
import net.kartikchawla.tic_tac_toe_masters.viewModels.GameBoardViewModel

class GameBoardFragment : Fragment() {
    private var _binding: FragmentGameBoardBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel : GameBoardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBoardBinding.inflate(inflater, container, false);
        val view = binding.root;
        val application = requireNotNull(this.activity).application
        val dao = GameDataBase.getInstance(application).gameDao
        val viewModelFactory = GameViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GameBoardViewModel::class.java)
        binding.gameBoardViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.gameOver.observe(viewLifecycleOwner, Observer { newValue ->
            if (newValue) {
                val action = GameBoardFragmentDirections.actionGameBoardFragmentToResultFragment(viewModel.result.value!!)
                viewModel.saveGame()
                view.findNavController().navigate(directions = action)
            }
        })
        return view
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}