package com.gramzin.movies.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gramzin.movies.R
import com.gramzin.movies.data.MovieAdapter
import com.gramzin.movies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MovieAdapter()
        binding.moviesRcView.adapter = adapter
    }
}