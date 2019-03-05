package com.vvmp.coinprediction.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vvmp.coinprediction.R
import com.vvmp.coinprediction.util.Key
import kotlinx.android.synthetic.main.activity_coin.*

class CoinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin)

        val name: String
        if (intent.getStringExtra(Key.USER_NAME) != null) { // Fetch the entered name from the intent
            name = intent.getStringExtra(Key.USER_NAME)
        } else { // If the no user name not add the anonymous string
            name = "Anonymous"
        }
        setWelcomeMessage(name)
    }

    /*
    Sets the name to the welcome message
     */
    private fun setWelcomeMessage(name: String) {
        text_welcome_message.text = getString(R.string.welcome_name_template, name)
    }
}
