package com.abhijith.note_taking

import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(true)
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
        val cardView = findViewById<MaterialCardView>(R.id.materialCardView)
        val shapeDrawable = GradientDrawable()
        shapeDrawable.shape = GradientDrawable.RECTANGLE
        shapeDrawable.cornerRadii = floatArrayOf(
            resources.getDimension(R.dimen.top_left_radius),
            resources.getDimension(R.dimen.top_left_radius),
            resources.getDimension(R.dimen.top_right_radius),
            resources.getDimension(R.dimen.top_right_radius),
            resources.getDimension(R.dimen.bottom_right_radius),
            resources.getDimension(R.dimen.bottom_right_radius),
            resources.getDimension(R.dimen.bottom_left_radius),
            resources.getDimension(R.dimen.bottom_left_radius)
        )
        cardView.background = shapeDrawable
    }
}
