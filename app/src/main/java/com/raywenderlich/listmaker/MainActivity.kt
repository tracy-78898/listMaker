package com.raywenderlich.listmaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.raywenderlich.listmaker.R.layout.fragment_main

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(fragment_main)
    }
}