package net.kartikchawla.tic_tac_toe_masters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import net.kartikchawla.tic_tac_toe_masters.databinding.FragmentGameBoardBinding
import net.kartikchawla.tic_tac_toe_masters.databinding.FragmentResultBinding
import net.kartikchawla.tic_tac_toe_masters.viewModelFactories.ResultViewModelFactory
import net.kartikchawla.tic_tac_toe_masters.viewModels.GameBoardViewModel
import net.kartikchawla.tic_tac_toe_masters.viewModels.ResultViewModel

class ResultFragment : Fragment() {
    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel : ResultViewModel
    lateinit var viewModelFactory: ResultViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false);
        val view = binding.root;
        val result = ResultFragmentArgs.fromBundle(requireArguments()).result
        viewModelFactory = ResultViewModelFactory(result)
        viewModel = ViewModelProvider(this, factory = viewModelFactory)[ResultViewModel::class.java]

        binding.resultViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.scoreListButton.setOnClickListener() {
            val action = ResultFragmentDirections.actionResultFragmentToScoreListFragment()
            view.findNavController().navigate(directions = action)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}