package hr.algebra.chordiato.presentation.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.chordiato.R
import hr.algebra.chordiato.databinding.FragmentFavouritesBinding
import kotlinx.coroutines.flow.collectLatest

class FavouritesFragment : Fragment() {

    private lateinit var binding: FragmentFavouritesBinding

    private val viewModel: FavouritesViewModel by viewModels()

    private lateinit var favouritesAdapter: FavouritesRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setObserver()
    }

    private fun setObserver() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { state ->
                favouritesAdapter.filterList(state.tracks)
            }
        }
    }

    private fun setupRecyclerView() {
        favouritesAdapter = FavouritesRecyclerViewAdapter(emptyList()) {
            val bundle = Bundle().apply {
                putString("link", it.link)
            }
            findNavController().navigate(R.id.songs, bundle)
        }
        binding.rvFavourites.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = favouritesAdapter
        }
    }

}