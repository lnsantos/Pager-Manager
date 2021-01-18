package com.lnsantos.pagermanager.manager.enums

import androidx.annotation.AnimRes
import com.lnsantos.pagermanager.R

enum class TransitionPageAnimation(
    @AnimRes val inTransition: Int,
    @AnimRes val outTransition: Int
) {
    SLIDE_LEFT_TO_RIGHT(R.animator.slide_in_right, R.animator.slide_out_right),
    SLIDE_RIGHT_TO_LEFT(R.animator.slide_in_left, R.animator.slide_out_left),
    SLIDE_BOTTOM_TO_UP(R.animator.slide_in_bottom, R.animator.slide_out_top),
    STATIONARY(R.animator.stationary, R.animator.stationary);
}