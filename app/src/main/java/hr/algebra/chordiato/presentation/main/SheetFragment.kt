package hr.algebra.chordiato.presentation.main

import android.annotation.SuppressLint
import android.media.AudioRecord
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import hr.algebra.chordiato.R
import hr.algebra.chordiato.databinding.FragmentSheetBinding
import hr.algebra.chordiato.domain.use_case.GetSongUseCase
import kotlinx.coroutines.flow.collectLatest
import java.io.ByteArrayOutputStream

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
        setupListeners()
        setObservers()
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
                    Toast.makeText(requireContext(), "Error: ${state.error}", Toast.LENGTH_SHORT).show()
                    binding.ivLoading.clearAnimation()
                    binding.ivLoading.visibility = View.INVISIBLE
                    return@collectLatest
                }
                if (state.isLoading) {
                    binding.ivLoading.clearAnimation()
                    val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.pulse)
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
            val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.pulse)
            binding.ivLoading.startAnimation(animation)
            val encodedSample = recordSample()
            viewModel.onEvent(SheetEvent.Recognize(encodedSample))
        }

    }

    @SuppressLint("MissingPermission")
    private fun recordSample(): String {

        val mainBuffer = ByteArrayOutputStream()
        val minimumBufferSize =
            AudioRecord.getMinBufferSize(SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT)
        val audioRecord =
            AudioRecord(AUDIO_SOURCE, SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT, minimumBufferSize)
        // Set up a buffer to read the audio data into
        val readBuffer = ByteArray(minimumBufferSize)
        // Start recording
        audioRecord.startRecording()
        val endTime = System.currentTimeMillis() + 5000
        while (System.currentTimeMillis() < endTime) {
            val bytesRead = audioRecord.read(readBuffer, 0, minimumBufferSize)
            mainBuffer.write(readBuffer, 0, bytesRead)
        }
        audioRecord.stop()
        audioRecord.release()
        val byteArray = mainBuffer.toByteArray()
        return Base64.encodeToString(byteArray, Base64.NO_WRAP)
    }

}