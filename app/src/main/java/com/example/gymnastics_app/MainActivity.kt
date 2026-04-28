package com.example.gymnastics_app

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gymnastics_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val gymnastics = Gymnastics()
    private var isComplete = false
    private  var lastZone: Zone? = null

    private lateinit var binding: ActivityMainBinding // to bind elements/components

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        savedInstanceState?.let {
            this.isComplete = it.getBoolean("isComplete")
            lastZone = Zone.entries.getOrNull(it.getInt("lastZone", -1))
            gymnastics.restore(it.getInt("score"), it.getInt("currentElement"), it.getBoolean("isDeductionTaken"))
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupClickListener()
        updateUI(null)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isComplete", isComplete)
        outState.putInt("score", gymnastics.score)
        outState.putInt("currentElement", gymnastics.currentElement)
        outState.putBoolean("isDeductionTaken", gymnastics.isDeductionTaken)
        outState.putInt("lastZone", lastZone?.ordinal ?: -1)
    }

    private fun setupClickListener() {
        binding.btnPerform.setOnClickListener {
            Log.d("gymnastics", "Perform Button Clicked!")
            val result = gymnastics.perform()
            updateUI(result)
        }

        binding.btnDeduction.setOnClickListener {
            Log.d("gymnastics", "Deduciton Button Clicked!")
            gymnastics.applyDeduction()
            this.isComplete = false
            updateUI(null)
        }

        binding.btnReset.setOnClickListener {
            Log.d("gymnastics", "Rest Button Clicked!")
            gymnastics.reset()
            this.isComplete = false
            lastZone = null
            updateUI(null)
        }
    }

    private fun updateUI(result: PerformResult?) {
        if (result?.isComplete == true) this.isComplete = true
        if (result?.zone != null) lastZone = result.zone

        // Zone Highlight
        highlightZone(result?.zone ?: lastZone)

        binding.tvElement.text = "Element ${gymnastics.currentElement} / 10"
        binding.tvScore.text = gymnastics.score.toString()

        // Buttons Enable and Disable
        binding.btnPerform.isEnabled = !gymnastics.isDeductionTaken && !this.isComplete
        binding.btnDeduction.isEnabled = gymnastics.currentElement > 1 && !gymnastics.isDeductionTaken && !this.isComplete
        binding.btnReset.isEnabled = gymnastics.isDeductionTaken || this.isComplete


        binding.tvComplete.visibility = if (this.isComplete) View.VISIBLE else View.GONE
    }

    private fun highlightZone(zone: Zone?) {
        when (zone) {
            Zone.EASY -> {
                binding.tvZoneEasy.setBackgroundColor(getColor(android.R.color.holo_blue_light))
                binding.tvZoneIntermediate.setBackgroundColor(getColor(android.R.color.white))
                binding.tvZoneAdvance.setBackgroundColor(getColor(android.R.color.white))
            }
            Zone.INTERMEDIATE -> {
                binding.tvZoneIntermediate.setBackgroundColor(getColor(android.R.color.holo_green_light))
                binding.tvZoneEasy.setBackgroundColor(getColor(android.R.color.white))
                binding.tvZoneAdvance.setBackgroundColor(getColor(android.R.color.white))
            }
            Zone.ADVANCED -> {
                binding.tvZoneAdvance.setBackgroundColor(getColor(android.R.color.holo_orange_light))
                binding.tvZoneEasy.setBackgroundColor(getColor(android.R.color.white))
                binding.tvZoneIntermediate.setBackgroundColor(getColor(android.R.color.white))
            }
            null -> {
                binding.tvZoneEasy.setBackgroundColor(getColor(android.R.color.white))
                binding.tvZoneIntermediate.setBackgroundColor(getColor(android.R.color.white))
                binding.tvZoneAdvance.setBackgroundColor(getColor(android.R.color.white))
            }
        }
    }
}