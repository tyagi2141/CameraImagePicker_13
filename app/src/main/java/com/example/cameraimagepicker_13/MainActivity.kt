package com.example.cameraimagepicker_13

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.launch
import androidx.activity.viewModels
import com.example.cameraimagepicker_13.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {


    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<ExampleViewModel>()

    private val singlePhotoPickerLauncher =
        registerForActivityResult(PickSinglePhotoContract()) { imageUri: Uri? ->
            imageUri?.let(viewModel::setImageUri)

            Log.e("resultttt", "${imageUri}")
        }

    private fun pickPhoto() = singlePhotoPickerLauncher.launch()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.button.setOnClickListener {
            pickPhoto()
        }

    }


}