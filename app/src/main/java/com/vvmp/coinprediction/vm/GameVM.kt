package com.vvmp.coinprediction.vm

import androidx.lifecycle.ViewModel
import com.vvmp.coinprediction.model.Score

class GameVM : ViewModel() {
    lateinit var playerOneName: String
    lateinit var playerTwoName: String

    val scoreInfo = Score(0, 0)


}