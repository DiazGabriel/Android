package com.example.planetes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.R
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        goToNextPage()
    }

    fun goToNextPage() {
        button.setOnClickListener {
            val intent = Intent(this, PlanetesActivity::class.java)
            startActivity(intent);
        }
    }
}