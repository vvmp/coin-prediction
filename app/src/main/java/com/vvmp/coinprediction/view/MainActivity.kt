package com.vvmp.coinprediction.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.vvmp.coinprediction.R
import com.vvmp.coinprediction.util.Config

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestFullScreenView()
        setContentView(R.layout.activity_main)

        launchNameScreen()
    }

    /*
    Requests the Fullscreen view
     */
    private fun requestFullScreenView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    /*
    Launches the name screen
     */
    private fun launchNameScreen() {
        Handler().postDelayed({
            // TODO
            val intent = Intent(this@MainActivity, NameActivity::class.java)
            startActivity(intent)
            finish()
        }, Config.SPLASH_TIME)
    }
}
