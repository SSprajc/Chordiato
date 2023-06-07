package hr.algebra.chordiato.presentation.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.chordiato.R
import hr.algebra.chordiato.databinding.FragmentHistoryBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    private val viewModel: HistoryViewModel by viewModels()

    private lateinit var historyAdapter: HistoryRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
/*
        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { state ->
                historyAdapter.filterList(state.tracks)
            }
        }
 */
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        historyAdapter = HistoryRecyclerViewAdapter(emptyList()) {
            viewModel.onEvent(HistoryEvent.ToggleFavourite(it))
        }

        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = historyAdapter
        }

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                val tracks = state.tracks
                historyAdapter.filterList(state.tracks)
            }
        }

        //viewModel.onEvent(HistoryEvent.GetSongs)

/*

        GlobalScope.launch(Dispatchers.Main) {

            val tracks = withContext(Dispatchers.IO) {
                (ChordiatoApplication.instance.native.application.provideDatabaset().trackDao.getAllTracks()).map { it.toTrack() }
            }

            binding.rvHistory.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = HistoryRecyclerViewAdapter(tracks = tracks) {
                    viewModel.onEvent(HistoryEvent.ToggleFavourite(it))
                }
            }

        }
 */

    }

    private fun setupListeners() {

    }

}