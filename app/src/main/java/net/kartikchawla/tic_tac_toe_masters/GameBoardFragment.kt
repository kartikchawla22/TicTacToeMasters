package net.kartikchawla.tic_tac_toe_masters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import net.kartikchawla.tic_tac_toe_masters.ViewModels.GameBoardViewModel
import net.kartikchawla.tic_tac_toe_masters.databinding.FragmentGameBoardBinding

class GameBoardFragment : Fragment() {
    private var _binding: FragmentGameBoardBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel : GameBoardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBoardBinding.inflate(inflater, container, false);
        val view = binding.root;
        viewModel = ViewModelProvider(this).get(GameBoardViewModel::class.java)

//        binding.gameBoardViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
//
//        viewModel.gameOver.observe(viewLifecycleOwner, Observer { newValue ->
//            if (newValue) {
//                val action = GameBoardFragmentDirections.actionGameBoardFragmentToResultFragment(viewModel.wonLostMessage())
//                view.findNavController().navigate(directions = action)
//            }
//        })
//
//        binding.guessButton.setOnClickListener() {
//            viewModel.makeGuess(binding.guess.text.toString().uppercase())
//            binding.guess.text = null
//        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}