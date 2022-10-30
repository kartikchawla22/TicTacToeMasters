package net.kartikchawla.tic_tac_toe_masters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import net.kartikchawla.tic_tac_toe_masters.viewModels.HomeViewModel
import net.kartikchawla.tic_tac_toe_masters.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false);
        val view = binding.root;
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.newGameButton.setOnClickListener() {
            val action = HomeFragmentDirections.actionHomeFragmentToGameBoardFragment()
                view.findNavController().navigate(directions = action)
        }
        binding.scoreListButton.setOnClickListener() {
            val action = HomeFragmentDirections.actionHomeFragmentToScoreListFragment()
            view.findNavController().navigate(directions = action)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}