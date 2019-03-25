package com.vvmp.coinprediction.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.vvmp.coinprediction.R
import com.vvmp.coinprediction.databinding.ActivityGameBinding
import com.vvmp.coinprediction.util.Key
import com.vvmp.coinprediction.vm.GameVM

class GameActivity : AppCompatActivity() {

    private lateinit var mGameVM: GameVM
    private lateinit var mBinding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_game)

        mGameVM = ViewModelProviders.of(this).get(GameVM::class.java)
        getPlayerNames()

        mBinding.viewModel = mGameVM
    }

    private fun getPlayerNames() {
        mGameVM.playerOneName = intent.getStringExtra(Key.PLAYER_ONE_NAME)
        mGameVM.playerTwoName = intent.getStringExtra(Key.PLAYER_TWO_NAME)
    }
}
