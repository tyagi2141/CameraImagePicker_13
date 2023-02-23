package com.example.cameraimagepicker_13

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.RequiresApi

private const val MIME_TYPE_IMAGE = "image/*"

class PickSinglePhotoContract : ActivityResultContract<Void?, Uri?>() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun createIntent(context: Context, input: Void?): Intent {

        return Intent(if (PhotoPickerAvailabilityChecker.isPhotoPickerAvailable()) {
            Intent(MediaStore.ACTION_PICK_IMAGES)
        } else {
            Intent(Intent.ACTION_OPEN_DOCUMENT)
        }).apply { type = MIME_TYPE_IMAGE }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        return intent.takeIf { resultCode == Activity.RESULT_OK }?.data
    }
}