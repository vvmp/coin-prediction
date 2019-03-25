package com.vvmp.coinprediction.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
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
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class GameActivity : AppCompatActivity() {

    private lateinit var mGameVM: GameVM
    private lateinit var mBinding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_game)

        mGameVM = ViewModelProviders.of(this).get(GameVM::class.java)
        getPlayerNames()
        initSubscribers()

        mBinding.viewModel = mGameVM
    }

    private fun getPlayerNames() {
//        mGameVM.playerOneName = intent.getStringExtra(Key.PLAYER_ONE_NAME)
//        mGameVM.playerTwoName = intent.getStringExtra(Key.PLAYER_TWO_NAME)
        mGameVM.playerOneName = "Jack"
        mGameVM.playerTwoName = "John"
    }

    private fun initSubscribers() {
        mGameVM.playing.observe(this, Observer {
            if (it != GameVM.PLAYING_STATE.IDLE) {
                askHeadOrTails()
            }
        })
        mGameVM.flipping.observe(this, Observer {
            when (it) {
                GameVM.FLIPPING_STATE.IDLE -> {
//                    text_state.visibility = View.GONE
                    text_state.text = "IDLE state"
                }

                GameVM.FLIPPING_STATE.FLIPPING -> {
                    text_state.visibility = View.VISIBLE
                    text_state.text = "Flipping"
                }

                GameVM.FLIPPING_STATE.FLIPPED -> {
                    text_state.visibility = View.VISIBLE
                    text_state.text = mGameVM.tossCoin().toString()
                }
            }
        })
        mGameVM.playing.value = GameVM.PLAYING_STATE.PLAYER_1
    }

    private fun askHeadOrTails() {
        val builder = AlertDialog.Builder(this)
        builder.setView(R.layout.layout_head_or_tail)
        builder.setTitle(getDialogTitle());
        val dialog = builder.create()
        dialog.show()

        dialog.findViewById<TextView>(R.id.action_head)?.setOnClickListener({
            dialog.dismiss()
            actionClicked(0)
        })

        dialog.findViewById<TextView>(R.id.action_tails)?.setOnClickListener({
            dialog.dismiss()
            actionClicked(1)
        })
    }

    private fun getDialogTitle(): String {
        val state = mGameVM.playing.value
        var playername = ""
        if (state == GameVM.PLAYING_STATE.PLAYER_1) {
            playername = mGameVM.playerOneName
        } else if (state == GameVM.PLAYING_STATE.PLAYER_2) {
            playername = mGameVM.playerOneName
        }
        return getString(R.string.player_turn, playername)
    }

    private fun actionClicked(id: Int) {
        val message: String
        if (id == 0) {
            message = "Heads tapped"
        } else {
            message = "Tails tapped"
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
