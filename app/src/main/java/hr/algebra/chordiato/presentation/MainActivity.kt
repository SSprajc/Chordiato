package hr.algebra.chordiato.presentation

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import hr.algebra.chordiato.R
import hr.algebra.chordiato.databinding.ActivityMainBinding
import hr.algebra.chordiato.presentation.favourites.FavouritesFragment
import hr.algebra.chordiato.presentation.history.HistoryFragment
import hr.algebra.chordiato.presentation.main.SheetFragment

const val REQ_CODE = 123


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupPermission()
        setupNavigation()
    }

    private fun setupNavigation() {
        navigateWithFragmentManager(SheetFragment())
        binding.appBar.apply {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.history -> {
                        navigateWithFragmentManager(HistoryFragment())
                        true
                    }
                    R.id.favourites -> {
                        navigateWithFragmentManager(FavouritesFragment())
                        true
                    }
                    R.id.songs -> {
                        navigateWithFragmentManager(SheetFragment())
                        true
                    }
                    else -> false
                }
            }
        }
        binding.appBar.selectedItemId = R.id.songs
    }

    private fun navigateWithFragmentManager(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frag, fragment)
        }.commit()
    }

    private fun setupPermission() {
        val permission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.RECORD_AUDIO,
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
            arrayOf(android.Manifest.permission.RECORD_AUDIO),
            REQ_CODE)
    }
}