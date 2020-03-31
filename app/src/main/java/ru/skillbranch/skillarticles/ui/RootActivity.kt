package ru.skillbranch.skillarticles.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.skillbranch.skillarticles.R

class RootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.houses)
    }
}