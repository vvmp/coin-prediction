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
            val intent = Intent(this@NameActivity, CoinActivity::class.java)
            intent.putExtra(Key.USER_NAME, getName()) // Put the entered name into the Intent
            startActivity(intent) // Launches the Coin count screen
            finish()
        }
    }

    /*
    Fetches name from EditText
     */
    private fun getName(): String {
        return edit_text_username.text.toString()
    }
}
