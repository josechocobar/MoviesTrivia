package com.josechocobar.moviestrivia.ui.animations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View


class Turn {
    fun getOutByLeft (view: View) :AnimatorSet{
        val animatorSet = AnimatorSet()
        val width = -view.width.toFloat()

        val oAnimator:ObjectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        val oAnimator2:ObjectAnimator = ObjectAnimator.ofFloat(view, "translationX", 0f, width / 4f)

        animatorSet.playTogether(oAnimator, oAnimator2)
        return animatorSet
    }




}