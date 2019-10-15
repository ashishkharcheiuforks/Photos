package com.github.sikv.photos.util

import android.animation.Animator
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.customTag(): String {
    return this::class.java.simpleName
}

fun Snackbar.setTextColor(@ColorRes color: Int): Snackbar {
    this.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            .setTextColor(ContextCompat.getColor(context, color))
    return this
}

fun Snackbar.setBackgroundColor(@ColorRes color: Int): Snackbar {
    this.view.setBackgroundColor(ContextCompat.getColor(context, color))
    return this
}

fun View.setVisibilityAnimated(newVisibility: Int, duration: Long = 500L) {
    animate()
            .alpha(if (newVisibility == View.VISIBLE) 1.0F else 0.0F)
            .setDuration(duration)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animator: Animator?) { }

                override fun onAnimationEnd(animator: Animator?) {
                    if (newVisibility != View.VISIBLE) {
                       visibility = View.GONE
                    }
                }

                override fun onAnimationCancel(animator: Animator?) { }

                override fun onAnimationStart(animator: Animator?) {
                    if (newVisibility == View.VISIBLE) {
                        visibility = View.VISIBLE
                    }
                }
            })
}