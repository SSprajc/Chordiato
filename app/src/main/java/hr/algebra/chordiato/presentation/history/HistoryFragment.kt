package hr.algebra.chordiato.presentation.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.chordiato.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    private val viewModel: HistoryViewModel by viewModels()

    private lateinit var historyAdapter: HistoryRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setObserver()
        viewModel.onEvent(HistoryEvent.GetSongs)
    }

    private fun setObserver() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                historyAdapter.filterList(state.tracks)
            }
        }
    }

    private fun setupRecyclerView() {
        historyAdapter = HistoryRecyclerViewAdapter(emptyList()) {
            viewModel.onEvent(HistoryEvent.ToggleFavourite(it))
        }
        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = historyAdapter
        }
    }
}