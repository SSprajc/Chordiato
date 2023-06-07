package hr.algebra.chordiato.presentation.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import hr.algebra.chordiato.R
import hr.algebra.chordiato.databinding.FragmentFavouritesBinding
import hr.algebra.chordiato.presentation.main.SheetFragment
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
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.appBar)
        favouritesAdapter = FavouritesRecyclerViewAdapter(emptyList()) {
            val bundle = Bundle().apply {
                putString("link", it.link)
            }
            bottomNav?.selectedItemId = R.id.songs
            val mainFrag = SheetFragment()
            mainFrag.arguments = bundle
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frag, mainFrag)
            }.commit()

        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFavourites.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = favouritesAdapter
        }

        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { state ->
                favouritesAdapter.filterList(state.tracks)
            }
        }

    }

}