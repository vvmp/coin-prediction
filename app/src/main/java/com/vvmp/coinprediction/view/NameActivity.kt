package com.vvmp.coinprediction.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vvmp.coinprediction.R
import com.vvmp.coinprediction.util.Key
import kotlinx.android.synthetic.main.activity_name.*

class NameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        btn_action_next.setOnClickListener {
            val intent = Intent(this@NameActivity, GameActivity::class.java)
            intent.putExtra(Key.PLAYER_ONE_NAME, getPlayerOneName())
            intent.putExtra(Key.PLAYER_TWO_NAME, getPlayerTwoName())
            startActivity(intent) // Launches the Game screen
            finish()
        }
    }

    private fun getPlayerOneName(): String {
        var name = edt_player_name_1?.text?.toString()
        if (name == null) name = "John"
        return name
    }

    private fun getPlayerTwoName(): String {
        var name = edt_player_name_2?.text?.toString()
        if (name == null) name = "Jack"
        return name
    }
}
