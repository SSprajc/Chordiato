package hr.algebra.chordiato.presentation.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import hr.algebra.chordiato.R
import hr.algebra.chordiato.core.util.AudioRecorderUtility
import hr.algebra.chordiato.databinding.FragmentSheetBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SheetFragment : Fragment() {

    private lateinit var binding: FragmentSheetBinding

    private val viewModel: SheetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setupListeners()
        setupWebView()
        //Displaying sheet when navigated from favourites frag
        arguments?.let {
            val link = it.getString("link")
            link?.let { url ->
                binding.webView.loadUrl(url)
                binding.webView.visibility = View.VISIBLE
            }
        }
    }

    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { state ->
                if (state.error.isNotBlank()) {
                    Toast.makeText(requireContext(), "Error: ${state.error}", Toast.LENGTH_SHORT)
                        .show()
                    binding.ivLoading.clearAnimation()
                    binding.ivLoading.visibility = View.INVISIBLE
                    return@collectLatest
                }
                if (state.isLoading) {
                    binding.ivLoading.clearAnimation()
                    val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate)
                    binding.ivLoading.startAnimation(animation)
                }
                if (state.sheetUrl.isNotBlank()) {
                    binding.webView.loadUrl(state.sheetUrl)
                    binding.webView.visibility = View.VISIBLE
                    binding.ivLoading.clearAnimation()
                    binding.ivLoading.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun setupListeners() {
        binding.fabRecognize.setOnClickListener {
            binding.ivLoading.clearAnimation()
            binding.ivLoading.visibility = View.VISIBLE
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate)
            binding.ivLoading.startAnimation(animation)
            //AudioRecorderUtil consists of android system classes
            //so its place is in the fragment, not view model
            GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    val encodedSample = AudioRecorderUtility.recordSample()
                    viewModel.onEvent(SheetEvent.Recognize(encodedSample))
                }
            }
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        binding.webView.settings.javaScriptEnabled = true
    }

}