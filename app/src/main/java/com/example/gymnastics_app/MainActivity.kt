package com.example.gymnastics_app

import android.content.res.ColorStateList
import android.graphics.Color
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
            Log.d("gymnastics", "Score: ${gymnastics.score}, Element: ${gymnastics.currentElement}")
        }

        binding.btnDeduction.setOnClickListener {
            Log.d("gymnastics", "Deduction Button Clicked!")
            gymnastics.applyDeduction()
            this.isComplete = false
            updateUI(null)
            Log.d("gymnastics", "Score: ${gymnastics.score}, Element: ${gymnastics.currentElement}")
        }

        binding.btnReset.setOnClickListener {
            Log.d("gymnastics", "Rest Button Clicked!")
            gymnastics.reset()
            this.isComplete = false
            lastZone = null
            updateUI(null)
            Log.d("gymnastics", "Score: ${gymnastics.score}, Element: ${gymnastics.currentElement}")
        }
    }

    private fun updateUI(result: PerformResult?) {
        if (result?.isComplete == true) this.isComplete = true
        if (result?.zone != null) lastZone = result.zone

        // Zone Highlight
        highlightZone(result?.zone ?: lastZone)
        highlightCircle(result?.zone ?: lastZone)


        binding.tvElement.text = getString(R.string.element_counter, gymnastics.currentElement)
        binding.tvScore.text = getString(R.string.score_display, gymnastics.score)

        // Buttons Enable and Disable
        binding.btnPerform.isEnabled = !gymnastics.isDeductionTaken && !this.isComplete
        binding.btnDeduction.isEnabled = gymnastics.currentElement > 1 && !gymnastics.isDeductionTaken && !this.isComplete
        binding.btnReset.isEnabled = gymnastics.isDeductionTaken || this.isComplete


        binding.tvComplete.visibility = if (this.isComplete) View.VISIBLE else View.GONE
    }

    private fun highlightZone(zone: Zone?) {

        // Reset all zones to unselected state first
        binding.tvZoneEasy.setBackgroundResource(0)
        binding.tvZoneEasy.setTextColor(getColor(android.R.color.darker_gray))
        binding.tvZoneEasy.setTypeface(null, android.graphics.Typeface.NORMAL)

        binding.tvZoneIntermediate.setBackgroundResource(0)
        binding.tvZoneIntermediate.setTextColor(getColor(android.R.color.darker_gray))
        binding.tvZoneIntermediate.setTypeface(null, android.graphics.Typeface.NORMAL)

        binding.tvZoneAdvance.setBackgroundResource(0)
        binding.tvZoneAdvance.setTextColor(getColor(android.R.color.darker_gray))
        binding.tvZoneAdvance.setTypeface(null, android.graphics.Typeface.NORMAL)

        when (zone) {
            Zone.EASY -> {
                binding.tvZoneEasy.setBackgroundResource(R.drawable.bg_pill_blue)
                binding.tvZoneEasy.setTextColor(getColor(android.R.color.white))
                binding.tvZoneEasy.setTypeface(null, android.graphics.Typeface.BOLD)
            }
            Zone.INTERMEDIATE -> {
                binding.tvZoneIntermediate.setBackgroundResource(R.drawable.bg_pill_green)
                binding.tvZoneIntermediate.setTextColor(getColor(android.R.color.white))
                binding.tvZoneIntermediate.setTypeface(null, android.graphics.Typeface.BOLD)
            }
            Zone.ADVANCED -> {
                binding.tvZoneAdvance.setBackgroundResource(R.drawable.bg_pill_orange)
                binding.tvZoneAdvance.setTextColor(getColor(android.R.color.white))
                binding.tvZoneAdvance.setTypeface(null, android.graphics.Typeface.BOLD)
            }
            null -> {
                // all reset above
                // due to code duplication and no needed
                // null branch will be empty
            }
        }
    }

    private fun highlightCircle(zone: Zone?) {
        if (gymnastics.isDeductionTaken) {
            // Deduction state: red border + red score text
            binding.scoreCircle.setBackgroundResource(R.drawable.circle_border_red)
            binding.tvScore.setTextColor(getColor(android.R.color.holo_red_dark))
            binding.cardDeductionBadge.visibility = View.VISIBLE
        } else {
            // Match circle color to current zone
            binding.cardDeductionBadge.visibility = View.GONE
            when (zone) {
                Zone.EASY -> {
                    binding.scoreCircle.setBackgroundResource(R.drawable.circle_border_blue)
                    binding.tvScore.setTextColor(getColor(android.R.color.holo_blue_dark))
                }
                Zone.INTERMEDIATE -> {
                    binding.scoreCircle.setBackgroundResource(R.drawable.circle_border_green)
                    binding.tvScore.setTextColor(getColor(android.R.color.holo_green_dark))
                }
                Zone.ADVANCED -> {
                    binding.scoreCircle.setBackgroundResource(R.drawable.circle_border_orange)
                    binding.tvScore.setTextColor(getColor(android.R.color.holo_orange_dark))
                }
                null -> {
                    binding.scoreCircle.setBackgroundResource(R.drawable.circle_border_blue)
                    binding.tvScore.setTextColor(getColor(android.R.color.holo_blue_dark))
                }
            }

            // Change perform btn color
            //when (zone) {
            //    Zone.EASY -> binding.btnPerform.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#1565C0"))
            //    Zone.INTERMEDIATE -> binding.btnPerform.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#2E7D32"))
            //    Zone.ADVANCED -> binding.btnPerform.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#E65100"))
            //    null -> binding.btnPerform.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#1565C0"))
            //}
        }
    }
}