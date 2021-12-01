package com.josechocobar.moviestrivia.ui.animations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

class Rebound {
    fun enter (view: View) : AnimatorSet {
        val animatorSet = AnimatorSet()
        val oAnimator: ObjectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f, 1f, 1f)
        val oAnimator2:ObjectAnimator = ObjectAnimator.ofFloat(view,  "scaleX", 0.3f, 1.05f, 0.9f, 1f)
        val oAnimator3:ObjectAnimator = ObjectAnimator.ofFloat(view, "scaleY", 0.3f, 1.05f, 0.9f, 1f)
        animatorSet.playTogether(oAnimator, oAnimator2, oAnimator3)
        return animatorSet
    }
}