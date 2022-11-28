package net.kartikchawla.tic_tac_toe_masters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import net.kartikchawla.tic_tac_toe_masters.databinding.FragmentScoreListBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import net.kartikchawla.tic_tac_toe_masters.database.GameDataBase
import net.kartikchawla.tic_tac_toe_masters.viewModelFactories.ScoreListViewModelFactory
import net.kartikchawla.tic_tac_toe_masters.viewModels.ScoreListViewModel

class ScoreListFragment : Fragment() {
    private var _binding: FragmentScoreListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentScoreListBinding.inflate(inflater,container,false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = GameDataBase.getInstance(application).gameDao
        val viewModelFactory = ScoreListViewModelFactory(dao)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(ScoreListViewModel::class.java)

        binding.scoreViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = ScoreItemAdapter()
        binding.scoreList.adapter = adapter

        viewModel.scores.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.homeButton.setOnClickListener() {
            val action = ScoreListFragmentDirections.actionScoreListFragmentToHomeFragment()
            view.findNavController().navigate(directions = action)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}