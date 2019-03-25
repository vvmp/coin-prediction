package com.vvmp.coinprediction.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vvmp.coinprediction.R
import kotlinx.android.synthetic.main.activity_name.*

class NameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        btn_action_next.setOnClickListener {
            // TODO - Navigate to the game Activity
        }
    }
}
