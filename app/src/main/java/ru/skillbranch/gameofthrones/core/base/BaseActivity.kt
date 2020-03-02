package ru.skillbranch.gameofthrones.core.base

import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.Copy
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.android.subKodein

abstract class BaseActivity : AppCompatActivity(), KodeinAware {

    override val kodein by subKodein(kodein(), copy = Copy.None) {

    }
}