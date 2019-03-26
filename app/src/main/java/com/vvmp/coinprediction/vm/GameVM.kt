package com.vvmp.coinprediction.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vvmp.coinprediction.model.Score
import com.vvmp.coinprediction.util.Config
import java.util.*
import kotlin.concurrent.schedule

class GameVM : ViewModel() {
    lateinit var playerOneName: String
    lateinit var playerTwoName: String

    val scoreViewInfo = Score(0, 0)
    val playing = MutableLiveData<PlayingState>()
    val flipping = MutableLiveData<FlippingState>()
    val scoreRecord = ArrayList<ScoreInfo>()

    private val timer = Timer()
    private var tmpScoreInfo: ScoreInfo? = null

    init {
        playing.value = PlayingState.IDLE
        flipping.value = FlippingState.IDLE
    }

    /* States the currently playing state */
    enum class PlayingState {
        IDLE,
        PLAYER_1,
        PLAYER_2
    }

    /* States the currently coin flipping state */
    enum class FlippingState {
        IDLE,
        FLIPPING,
        FLIPPED
    }

    /* Represents the COIN state */
    enum class Coin {
        HEAD,
        TAIL
    }

    /* Represents the PLAYER */
    enum class PLAYER {
        ONE,
        TWO
    }

    /* Tosses the coin */
    private fun tossCoin(): Coin {
        val random: Int = (0..10).random()
        val result: Coin
        if (random.rem(2) == 0) {
            result = Coin.HEAD
        } else {
            result = Coin.TAIL
        }
        return result
    }

    /* Will be triggered when the flipping timer ends */
    private fun coinFlipEnds() {
        tmpScoreInfo?.result = tossCoin()
        val player: PLAYER
        if (tmpScoreInfo?.result == tmpScoreInfo?.chosen) {
            if (tmpScoreInfo?.player == PLAYER.ONE) {
                player = PLAYER.ONE
            } else {
                player = PLAYER.TWO
            }
        } else {
            if (tmpScoreInfo?.player == PLAYER.ONE) {
                player = PLAYER.TWO
            } else {
                player = PLAYER.ONE
            }
        }
        addScore(player) // Update the user score
        addRecord(tmpScoreInfo) // Update the playing history record
        tmpScoreInfo = null // Empty the tmpScoreInfo
        flipping.postValue(GameVM.FlippingState.FLIPPED) // Change the flipping state
        setCurrentPlayer() // Set the current player
    }

    /* Will be triggered when one of the coin option was chosen by the player */
    fun optionChosen(option: Coin) {
        tmpScoreInfo = ScoreInfo(getCurrentPlayer(), option, null)
        startTimer()
    }

    /* Update the respective players score*/
    private fun addScore(player: PLAYER) {
        if (player == PLAYER.ONE) {
            scoreViewInfo.scorePlayerOne = scoreViewInfo.scorePlayerOne + Config.SCORE_PER_WIN
        } else {
            scoreViewInfo.scorePlayerTwo = scoreViewInfo.scorePlayerTwo + Config.SCORE_PER_WIN
        }
    }

    /* Sets the current player */
    private fun setCurrentPlayer() {
        if (scoreRecord.isEmpty()) {
            playing.postValue(PlayingState.PLAYER_1)
        } else {
            if (scoreRecord[scoreRecord.size - 1].player == PLAYER.ONE) {
                playing.postValue(PlayingState.PLAYER_2)
            } else {
                playing.postValue(PlayingState.PLAYER_1)
            }
        }
    }

    /* Initiates the game */
    fun startGame() {
        playing.value = PlayingState.PLAYER_1
    }

    /* Returns the current player name */
    fun getCurrentPlayerName(): String {
        val playerName: String
        if (playing.value == PlayingState.PLAYER_1) {
            playerName = playerOneName
        } else {
            playerName = playerTwoName
        }
        return playerName
    }

    /* Returns the current player */
    private fun getCurrentPlayer(): PLAYER {
        val currentPlayer: PLAYER
        if (playing.value == PlayingState.PLAYER_1) {
            currentPlayer = PLAYER.ONE
        } else {
            currentPlayer = PLAYER.TWO
        }
        return currentPlayer
    }

    /* Initiates the flipping timer */
    private fun startTimer() {
        flipping.value = GameVM.FlippingState.FLIPPING
        timer.schedule(Config.FLIPPING_TIME) {
            coinFlipEnds()
        }
    }

    /* Adds the playing history record */
    private fun addRecord(score: ScoreInfo?) {
        if (score == null) {
            return
        }
        scoreRecord.add(score)
    }

    data class ScoreInfo(val player: PLAYER, val chosen: Coin, var result: Coin?)
}