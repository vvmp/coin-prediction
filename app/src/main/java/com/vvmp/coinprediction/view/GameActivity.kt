package com.vvmp.coinprediction.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vvmp.coinprediction.R
import com.vvmp.coinprediction.databinding.ActivityGameBinding
import com.vvmp.coinprediction.util.Key
import com.vvmp.coinprediction.vm.GameVM
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    private lateinit var mGameVM: GameVM
    private lateinit var mBinding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_game)

        mGameVM = ViewModelProviders.of(this).get(GameVM::class.java)
        getPlayerNames() // Retrieves the player name from the previous screen
        initSubscribers() // Initializes the live data subscribers and listeners

        mBinding.viewModel = mGameVM
    }

    /* Retrieves the player name from the previous screen */
    private fun getPlayerNames() {
        mGameVM.playerOneName = intent.getStringExtra(Key.PLAYER_ONE_NAME)
        mGameVM.playerTwoName = intent.getStringExtra(Key.PLAYER_TWO_NAME)
    }

    /* Initializes the live data subscribers and listeners */
    private fun initSubscribers() {
        mGameVM.playing.observe(this, Observer {
            if (it != GameVM.PlayingState.IDLE) {
                action_toss.isEnabled = true
            }
        })
        mGameVM.flipping.observe(this, Observer {
            when (it) {
                GameVM.FlippingState.IDLE -> {
                    coin_image.visibility = View.GONE
                    text_state.visibility = View.VISIBLE
                    text_state.text = getString(R.string.state_coin_idle)
                }

                GameVM.FlippingState.FLIPPING -> {
                    coin_image.visibility = View.GONE
                    text_state.visibility = View.VISIBLE
                    text_state.text = getString(R.string.state_coin_flipping)
                }

                GameVM.FlippingState.FLIPPED -> {
                    text_state.visibility = View.GONE
                    val tossResult = mGameVM.scoreRecord[mGameVM.scoreRecord.size - 1].result
                    val drawableResId: Int
                    if (tossResult == GameVM.Coin.HEAD) {
                        drawableResId = R.drawable.coin_indian_ten_rupee_heads
                    } else {
                        drawableResId = R.drawable.coin_indian_ten_rupee_tails
                    }

                    coin_image.setImageDrawable(getDrawable(drawableResId)) // Show the respective coin image
                    coin_image.visibility = View.VISIBLE
                }
            }
        })
        mGameVM.startGame()
        action_toss.setOnClickListener { tossAction() }
    }

    /* Asks the player for head or tail option */
    private fun askHeadOrTails() {
        val builder = AlertDialog.Builder(this)
        builder.setView(R.layout.layout_head_or_tail)
        builder.setTitle(getDialogTitle())
        val dialog = builder.create()
        dialog.show()

        dialog.findViewById<TextView>(R.id.action_head)?.setOnClickListener {
            dialog.dismiss()
            mGameVM.optionChosen(GameVM.Coin.HEAD)
        }

        dialog.findViewById<TextView>(R.id.action_tails)?.setOnClickListener {
            dialog.dismiss()
            mGameVM.optionChosen(GameVM.Coin.TAIL)
        }
        dialog.setCancelable(false)
    }

    /* Will be triggered when the toss button clicked */
    private fun tossAction() {
        action_toss.isEnabled = false
        askHeadOrTails()
        mGameVM.flipping.value = GameVM.FlippingState.IDLE
    }

    /* Forms the dialog title */
    private fun getDialogTitle() = getString(R.string.player_turn, mGameVM.getCurrentPlayerName())
}
