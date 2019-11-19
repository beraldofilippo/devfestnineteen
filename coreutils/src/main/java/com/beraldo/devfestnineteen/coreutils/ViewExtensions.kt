package com.beraldo.devfestnineteen.coreutils

import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

fun View.visible() {
    isVisible = true
}

fun View.gone() {
    isGone = true
}

fun View.invisible() {
    isInvisible = true
}