package com.josechocobar.moviestrivia.ui.animations

import android.animation.AnimatorSet
import android.view.animation.AccelerateInterpolator

class Renderizator (){
    var du: Long = 1000

    lateinit var animatorSet: AnimatorSet

    fun setAnimation (animatorSet: AnimatorSet){
        this.animatorSet = animatorSet
    }

    fun setDuration (duration: Long){
        this.du = duration
    }

    fun start(){
        animatorSet.duration = du
        animatorSet.interpolator = AccelerateInterpolator()
        animatorSet.start()
    }
}