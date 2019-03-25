package com.vvmp.coinprediction.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vvmp.coinprediction.model.Score

class GameVM : ViewModel() {
    lateinit var playerOneName: String
    lateinit var playerTwoName: String

    val scoreInfo = Score(0, 0)
    val playing = MutableLiveData<PLAYING_STATE>()
    val flipping = MutableLiveData<FLIPPING_STATE>()

    init {
        playing.value = PLAYING_STATE.IDLE
        flipping.value = FLIPPING_STATE.IDLE
    }

    enum class PLAYING_STATE {
        IDLE,
        PLAYER_1,
        PLAYER_2
    }

    enum class FLIPPING_STATE {
        IDLE,
        FLIPPING,
        FLIPPED
    }

    enum class Coin {
        HEAD,
        TAIL
    }

    fun startGame() {
        playing.value = PLAYING_STATE.PLAYER_1
    }

    fun tossCoin(): Coin {
        val random: Int = (0..10).random()
        if (random.rem(2) == 0) {
            return Coin.HEAD
        } else {
            return Coin.TAIL
        }
    }

    fun flipTheCoin() {
        // Will be triggered when the option chosen
        // Flip the coin
        // Check the result
        // Update the score
    }
}