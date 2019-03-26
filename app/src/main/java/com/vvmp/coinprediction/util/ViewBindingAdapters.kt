package com.vvmp.coinprediction.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.vvmp.coinprediction.R
import com.vvmp.coinprediction.vm.GameVM

@BindingAdapter("app:scoreEdt")
fun setScoreToEdt(view: TextView, score: Int) {
    view.text = score.toString()
}

@BindingAdapter("app:userTurn")
fun userTurn(view: TextView, viewModel: GameVM) {
    if (viewModel.playing.value == GameVM.PlayingState.PLAYER_1) {
        view.text = view.context.getString(R.string.player_turn, viewModel.playerOneName)
    } else if (viewModel.playing.value == GameVM.PlayingState.PLAYER_2) {
        view.text = view.context.getString(R.string.player_turn, viewModel.playerOneName)
    }
}