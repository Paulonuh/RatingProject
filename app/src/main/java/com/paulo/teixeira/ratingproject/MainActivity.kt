package com.paulo.teixeira.ratingproject

import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.paulo.teixeira.ratingproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val starViews =
        lazy { binding.run { listOf(cbStarOne, cbStarTwo, cbStarThree, cbStarFour, cbStarFive) } }

    private var countingStars: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        starViews.value.forEach { imageStarView ->
            imageStarView.setOnClickListener { view ->
                setLabelTextColor(R.color.black)
                drawSelectStar(view.id)
            }
        }
        binding.btCountingStart.setOnClickListener {
            binding.tvStartCount.text = getString(R.string.count_starts, countingStars)
        }
    }


    private fun drawSelectStar(starId: Int) {
        var countingStars = 0
        var isSelected = true
        starViews.value.forEach { starView ->
            if (isSelected) countingStars++

            starView.isSelected = isSelected
            if (starView.id == starId) {
                isSelected = false
            }
        }
        this.countingStars = countingStars
    }

    private fun setLabelTextColor(@ColorRes colorResId: Int) {
        val color = ContextCompat.getColor(this, colorResId)
        binding.tvLabel.setTextColor(color)
    }

}