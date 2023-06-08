package hr.algebra.chordiato.core.util

import android.annotation.SuppressLint
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.util.Base64
import java.io.ByteArrayOutputStream

object AudioRecorderUtility {
    private const val AUDIO_SOURCE = MediaRecorder.AudioSource.MIC
    private const val SAMPLE_RATE = 44100
    private const val CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO
    private const val AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT

    @SuppressLint("MissingPermission")
    fun recordSample(): String {
        val mainBuffer = ByteArrayOutputStream()
        val minimumBufferSize =
            AudioRecord.getMinBufferSize(SAMPLE_RATE,
                CHANNEL_CONFIG,
                AUDIO_FORMAT)
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