package com.example.homeworkunituitests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.dependencyinjections.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val result: TextView = findViewById(R.id.result)
        val button: Button = findViewById(R.id.button)
        val viewModel: MyViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        button.setOnClickListener {
            viewModel.getData()
        }

        viewModel.uiState.observe(this) { uiState ->
            when (uiState) {
                is MyViewModel.UiState.Empty -> result.text = getString(R.string.press_button)
                is MyViewModel.UiState.Processing -> result.text = getString(R.string.processing)
                is MyViewModel.UiState.Result -> result.text = uiState.bitcoinResult
                is MyViewModel.UiState.Error -> result.text = getString(R.string.eror)
            }
        }
    }
}
