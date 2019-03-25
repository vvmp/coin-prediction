package com.vvmp.coinprediction.util

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:scoreEdt")
fun setScoreToEdt(view: TextView, score: Int) {
    view.text = score.toString()
}