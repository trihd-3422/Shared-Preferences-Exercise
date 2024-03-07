package com.example.sharedpreferencesexercise

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.sharedpreferencesexercise.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var sharePre: SharedPreferences
    private var currentCount by Delegates.notNull<Int>()
    private var currentColor by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        sharePre = getSharedPreferences("Share", MODE_PRIVATE)
        val defaultColor = ContextCompat.getColor(this, R.color.default_background)
        val defaultCount = 0

        currentCount = sharePre.getInt("COUNT_KEY", defaultCount )
        currentColor = sharePre.getInt("COLOR_KEY", defaultColor)

        binding.countTextview.text = currentCount.toString()
        binding.countTextview.setBackgroundColor(currentColor)

        binding.blackBackgroundButton.setOnClickListener {
            binding.countTextview.setBackgroundColor(Color.BLACK)
            currentColor = Color.BLACK
        }
        binding.redBackgroundButton.setOnClickListener {
            binding.countTextview.setBackgroundColor(Color.RED)
            currentColor = Color.RED
        }
        binding.blueBackgroundButton.setOnClickListener {
            binding.countTextview.setBackgroundColor(Color.BLUE)
            currentColor = Color.BLUE
        }
        binding.greenBackgroundButton.setOnClickListener {
            binding.countTextview.setBackgroundColor(Color.GREEN)
            currentColor = Color.GREEN
        }

        binding.countButton.setOnClickListener {
            currentCount += 1
            binding.countTextview.text = currentCount.toString()
        }
        binding.resetButton.setOnClickListener {
            currentCount = defaultCount
            currentColor = defaultColor
            binding.countTextview.text = currentCount.toString()
            binding.countTextview.setBackgroundColor(defaultColor)
            sharePre.edit().clear().apply()
        }
    }

    override fun onPause() {
        super.onPause()
        sharePre.edit().putInt("COUNT_KEY", currentCount)
            .putInt("COLOR_KEY", currentColor)
            .apply()
    }
}