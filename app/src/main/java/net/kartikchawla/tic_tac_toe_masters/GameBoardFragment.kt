package net.kartikchawla.tic_tac_toe_masters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import net.kartikchawla.tic_tac_toe_masters.databinding.FragmentGameBoardBinding
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
        viewModel = ViewModelProvider(this)[GameBoardViewModel::class.java]

        binding.gameBoardViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.gameOver.observe(viewLifecycleOwner, Observer { newValue ->
            if (newValue) {
                val action = GameBoardFragmentDirections.actionGameBoardFragmentToResultFragment(viewModel.gameState.value!!)
                view.findNavController().navigate(directions = action)
            }
        })
        binding.button1.setOnClickListener{ squareTouched(binding.button1, 1)}
        binding.button2.setOnClickListener{ squareTouched(binding.button2, 2)}
        binding.button3.setOnClickListener{ squareTouched(binding.button3, 3)}
        binding.button4.setOnClickListener{ squareTouched(binding.button4, 4)}
        binding.button5.setOnClickListener{ squareTouched(binding.button5, 5)}
        binding.button6.setOnClickListener{ squareTouched(binding.button6, 6)}
        binding.button7.setOnClickListener{ squareTouched(binding.button7, 7)}
        binding.button8.setOnClickListener{ squareTouched(binding.button8, 8)}
        binding.button9.setOnClickListener{ squareTouched(binding.button9, 9)}
        return view
    }

    fun squareTouched(square: View, id: Int) {
        val turn = viewModel.squareTouchedHandler(square, id)
        (square as Button).text = turn
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}